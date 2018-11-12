import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Input, Button, TextField, FormControl, withStyles, FormGroup, InputLabel, NativeSelect } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { technicalObjectActions } from '../../redux/actions'

class EditTechnicalObject extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(technicalObjectActions.resetTechnicalTypes())
  }

  componentWillMount() {
    if (!this.props.loadedTechnicalObject) {
      this.loadTechnicalObject()
    }
  }

  loadTechnicalObject = () => {
    const { dispatch, match } = this.props
    const objectId = match.params.id
    dispatch(technicalObjectActions.findById(objectId))
  }

  loadTechnicalObjectTypes = () => {
    if (this.props.technicalObject != null) {
      if (this.props.technicalObject.parentObject == null) {
        this.props.dispatch(technicalObjectActions.getTypes())
      } else {
        const parentId = this.props.technicalObject.parentObject.id
        this.props.dispatch(technicalObjectActions.getTypes({ parentId }))
      }
    }
  }

  handleChange = (name) => (event) => {
    this.setState({ [name]: event.target.value })
  }

  handleCheck = (name) => (event) => {
    this.setState({ [name]: event.target.checked })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const name = this.state.name ? this.state.name : this.props.technicalObject.name
    const type = this.state.type ? this.state.type : this.props.technicalObject.type
    const version = this.state.version ? this.state.version : this.props.technicalObject.version
    const description = this.state.description ? this.state.description : this.props.technicalObject.description
    const { dispatch, match } = this.props
    if (name && type) {
      dispatch(technicalObjectActions.update({ id: match.params.id, name, type, version, description }))
    }
  }

  render() {
    const { loadedTechnicalObject, loadedTechnicalObjectTypes, technicalObjectTypes, technicalObject } = this.props
    if (!loadedTechnicalObject) {
      return null
    }
    if (!loadedTechnicalObjectTypes) {
      this.loadTechnicalObjectTypes()
      return null
    }
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField label="Nombre" name="name" defaultValue={technicalObject.name} onChange={this.handleChange('name')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Versión" name="version" defaultValue={technicalObject.version} onChange={this.handleChange('version')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="type">Tipo</InputLabel>
            <NativeSelect
              input={<Input name="type" id="type" />}
              label="Tipo"
              defaultValue={technicalObject.type}
              onChange={this.handleChange('type')}
            >
              <option value="">Tipo</option>
              {technicalObjectTypes.map(x => (
                <option key={x.id} value={x.translation}>{x.translation}</option>
              ))}
            </NativeSelect>
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Descripción" name="description" defaultValue={technicalObject.description} onChange={this.handleChange('description')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Actualizar Objeto
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
    loading,
    loadedTechnicalObject,
    loadedTechnicalObjectTypes,
    technicalObjectTypes,
    technicalObject,
  } = state.technicalObjectReducer
  return { loading, loadedTechnicalObject, loadedTechnicalObjectTypes, technicalObjectTypes, technicalObject }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(EditTechnicalObject)))
