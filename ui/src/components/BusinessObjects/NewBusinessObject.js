import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { withStyles, TextField, FormGroup, Button, InputLabel, NativeSelect, FormControl } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { businessObjectActions } from '../../redux/actions'

class NewBusinessObject extends Component {
  constructor(props) {
    super(props)
    this.state = {
      name: null,
      type: '',
      version: null,
      description: null,
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleChange = (name) => (event) => {
    this.setState({ [name]: event.target.value })
  }

  handleCheck = (name) => (event) => {
    this.setState({ [name]: event.target.checked })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { name, type, version, description } = this.state
    const { dispatch } = this.props
    if (name && type) {
      dispatch(businessObjectActions.create({ description, name, type, version }))
    }
  }

  render() {
    const { businessObjectTypes, loadedBusinessObjectTypes } = this.props
    if (!loadedBusinessObjectTypes) {
      return null
    }
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField label="Nombre" onChange={this.handleChange('name')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Versión" onChange={this.handleChange('version')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="type">Tipo</InputLabel>
            <NativeSelect
              value={this.state.type}
              onChange={this.handleChange('type')}
              inputProps={{ id: 'type' }}
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
            <TextField label="Descripción" onChange={this.handleChange('description')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Registrar Objeto de Negocio
              <Save className="icon-button" />
            </Button>
          </FormControl>
        </FormGroup>
      </form>
    )
  }
}

const mapStateToProps = state => {
  const { loadedBusinessObjectTypes, businessObjectTypes } = state.businessObjectReducer
  return { loadedBusinessObjectTypes, businessObjectTypes }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(NewBusinessObject)))
