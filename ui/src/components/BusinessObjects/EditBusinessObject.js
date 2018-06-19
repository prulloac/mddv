import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Input, Button, TextField, FormControl, withStyles, FormGroup, InputLabel, NativeSelect } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { businessObjectActions } from '../../redux/actions'

class EditBusinessObject extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  componentWillMount() {
    if (!this.props.loadedBusinessObject) {
      this.loadBusinessObject()
    }
  }

  loadBusinessObject = () => {
    const { dispatch, match } = this.props
    const objectId = match.params.id
    dispatch(businessObjectActions.findById(objectId))
  }

  handleChange = (name) => (event) => {
    this.setState({ [name]: event.target.value })
  }

  handleCheck = (name) => (event) => {
    this.setState({ [name]: event.target.checked })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const name = this.state.name ? this.state.name : this.props.repository.name
    const type = this.state.type ? this.state.type : this.props.repository.type
    const version = this.state.version ? this.state.version : this.props.repository.version
    const description = this.state.description ? this.state.description : this.props.repository.description
    const { dispatch, match } = this.props
    if (name && type) {
      dispatch(businessObjectActions.update({ id: match.params.id, name, type, version, description }))
    }
  }

  render() {
    const { loadedBusinessObject, businessObjectTypes, businessObject } = this.props
    if (!loadedBusinessObject) {
      return null
    }
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField label="Nombre" name="name" defaultValue={businessObject.name} onChange={this.handleChange('name')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Versión" name="version" defaultValue={businessObject.version} onChange={this.handleChange('version')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="type">Tipo</InputLabel>
            <NativeSelect
              input={<Input name="type" id="type" />}
              label="Tipo"
              defaultValue={businessObject.type}
              onChange={this.handleChange('type')}
            >
              <option value="">Tipo</option>
              {businessObjectTypes.map(x => (
                <option key={x} value={x}>{x}</option>
              ))}
            </NativeSelect>
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Descripción" name="description" defaultValue={businessObject.description} onChange={this.handleChange('description')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Actualizar Repositorio
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
    loadedBusinessObject,
    loadedBusinessObjectTypes,
    businessObjectTypes,
    businessObject,
  } = state.businessObjectReducer
  return { loading, loadedBusinessObject, loadedBusinessObjectTypes, businessObjectTypes, businessObject }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(EditBusinessObject)))
