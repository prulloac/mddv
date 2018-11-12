import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Button, TextField, FormControl, withStyles, FormGroup, ListItemText, Select, MenuItem, Checkbox } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { businessObjectActions, technicalObjectActions } from '../../redux/actions'

class RelateTechnicalObject extends Component {
  constructor(props) {
    super(props)
    this.state = {
      relatedObjects: null,
    }
    this.props.dispatch(technicalObjectActions.findById(this.props.match.params.id))
    if (!this.props.loadedBusinessObjects) {
      this.props.dispatch(businessObjectActions.getAll())
    }
    if (!this.props.loadedTechnicalObjects) {
      this.props.dispatch(technicalObjectActions.getAll())
    }
  }

  componentWillMount() {
    this.checkRelated()
  }

  handleChange = (name) => (event) => {
    this.setState({ [name]: event.target.value })
  }

  handleCheck = (name) => (event) => {
    this.setState({ [name]: event.target.checked })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { relatedObjects } = this.state
    const { dispatch, match } = this.props
    dispatch(businessObjectActions.updateRelations({ id: match.params.id, relatedObjects }))
  }

  checkRelated = () => {
    console.log('test')
    if (!this.props.loadedBusinessObjects ||
      !this.props.loadedTechnicalObject ||
      !this.props.loadedTechnicalObjects) {
      setTimeout(() => this.checkRelated(), 1000)
      return
    }
    const objects = [].concat(this.props.technicalObjects, this.props.businessObjects)
    if (this.state.relatedObjects === null) {
      const relatedObjects = objects.filter(x =>
        this.props.technicalObject.linkedTo.some(y => y === x.id))
      this.setState({ relatedObjects })
    }
  }

  render() {
    const { technicalObject,
      businessObjects,
      technicalObjects,
    } = this.props
    const { relatedObjects } = this.state
    if (relatedObjects === null) {
      return null
    }
    const objects = [].concat(businessObjects, technicalObjects)
    console.log(relatedObjects)
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField disabled label="Nombre" name="name" defaultValue={technicalObject.name} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField disabled label="Versión" name="version" defaultValue={technicalObject.version} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField disabled label="Tipo" name="type" defaultValue={technicalObject.type} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <Select
            multiple
            value={relatedObjects}
            onChange={this.handleChange('relatedObjects')}
            renderValue={selected => selected.map(x => x.name).join(', ')}
          >
            {objects.map(x => (
              <MenuItem key={x.id} value={x}>
                <Checkbox checked={relatedObjects.indexOf(x) > -1} />
                <ListItemText primary={x.name} secondary={x.type} />
              </MenuItem>
            ))}
          </Select>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Actualizar Relaciones
              <Save className="icon-button" />
            </Button>
          </FormControl>
        </FormGroup>
      </form>
    )
  }
}

const mapStateToProps = state => {
  const {
    loadedBusinessObjects,
    businessObjects,
  } = state.businessObjectReducer
  const {
    loading,
    loadedTechnicalObject,
    loadedTechnicalObjects,
    technicalObjects,
    technicalObject,
  } = state.technicalObjectReducer
  return { loading,
    loadedBusinessObjects,
    businessObjects,
    technicalObject,
    loadedTechnicalObject,
    loadedTechnicalObjects,
    technicalObjects,
  }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(RelateTechnicalObject)))
