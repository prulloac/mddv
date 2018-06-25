import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { withStyles, TextField, FormGroup, Button, InputLabel, NativeSelect, FormControl, FormControlLabel, Switch } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { repositoryActions } from '../../redux/actions'

class NewRepositoryForm extends Component {
  constructor(props) {
    super(props)
    this.state = {
      name: null,
      engine: '',
      location: null,
      version: null,
      outsourced: false,
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
    const { name, engine, version, location, outsourced } = this.state
    const { dispatch } = this.props
    if (name && engine) {
      dispatch(repositoryActions.create({ location: outsourced ? location : null, name, engine, version, outsourced }))
    }
  }

  render() {
    const { extractableEngines, loadedExtractables } = this.props
    if (!loadedExtractables) {
      return null
    }
    const extractableVersions = this.state.engine.length > 0
      ? extractableEngines
        .filter(x => x.engine === this.state.engine)
        .map(x => x.versions)[0]
        .sort((x, y) => parseFloat(x) - parseFloat(y))
        .map(x => (
          <option key={x} value={x}>{x}</option>
        )) : null
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField label="Nombre" onChange={this.handleChange('name')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="engine">Tipo (Motor)</InputLabel>
            <NativeSelect
              value={this.state.engine}
              onChange={this.handleChange('engine')}
              inputProps={{ id: 'engine' }}
            >
              <option value="">Tipo</option>
              {extractableEngines.map(x => (
                <option key={x.engine} value={x.engine}>{x.engine}</option>
              ))}
            </NativeSelect>
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="version">Versión</InputLabel>
            <NativeSelect
              value={this.state.engine}
              onChange={this.handleChange('version')}
              inputProps={{ id: 'version' }}
            >
              <option value="">Versión</option>
              {extractableVersions}
            </NativeSelect>
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControlLabel
            control={
              <Switch onChange={this.handleCheck('outsourced')} />
            }
            label="Externalizado"
          />
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Ubicación" disabled={!this.state.outsourced} onChange={this.handleChange('location')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Registrar Repositorio
              <Save className="icon-button" />
            </Button>
          </FormControl>
        </FormGroup>
      </form>
    )
  }
}

const mapStateToProps = state => {
  const { loadedExtractables, extractableEngines } = state.repositoryReducer
  return { loadedExtractables, extractableEngines }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(NewRepositoryForm)))
