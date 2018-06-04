import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Input, Button, TextField, FormControl, withStyles, FormGroup, InputLabel, NativeSelect, FormControlLabel, Switch } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { repositoryActions } from '../../redux/actions'

class EditRepository extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  componentWillMount() {
    if (!this.props.loadedRepository) {
      this.loadRepository()
    }
  }

  loadRepository = () => {
    const { dispatch, match } = this.props
    const repositoryId = match.params.id
    dispatch(repositoryActions.findById(repositoryId))
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
    const location = this.state.location ? this.state.location : this.props.repository.location
    const outsourced = this.state.outsourced ? this.state.outsourced : this.props.repository.outsourced
    const { dispatch, match } = this.props
    if (name && type) {
      dispatch(repositoryActions.update({ id: match.params.id, name, location, type, version, outsourced }))
    }
  }

  render() {
    const { loadedRepository, extractableEngines, repository } = this.props
    if (!loadedRepository) {
      return null
    }
    return (
      <form autoComplete="off" className="mddv-form">
        <FormGroup>
          <FormControl>
            <TextField label="Nombre" name="name" defaultValue={repository.name} onChange={this.handleChange('name')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <InputLabel htmlFor="type">Tipo</InputLabel>
            <NativeSelect
              input={<Input name="type" id="type" />}
              label="Tipo"
              defaultValue={repository.type}
              onChange={this.handleChange('type')}
            >
              <option value="">Tipo</option>
              {extractableEngines.map(x => (
                <option key={x.key} value={x.value}>{x.text}</option>
              ))}
            </NativeSelect>
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControlLabel
            control={
              <Switch defaultChecked={repository.outsourced} onChange={this.handleCheck('outsourced')} />
            }
            label="Externalizado"
          />
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Ubicación" name="location" defaultValue={repository.location} onChange={this.handleChange('location')} />
          </FormControl>
        </FormGroup>
        <FormGroup>
          <FormControl>
            <TextField label="Versión" name="location" defaultValue={repository.version} onChange={this.handleChange('version')} />
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
  const { loading, loadedRepository, loadedExtractables, extractableEngines, repository } = state.repositoryReducer
  return { loading, loadedRepository, loadedExtractables, extractableEngines, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(EditRepository)))
