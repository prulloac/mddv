import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Button, TextField, FormControl, withStyles, FormGroup, InputLabel, NativeSelect } from '@material-ui/core'
import { Save } from '@material-ui/icons'
import { repositoryActions } from '../../redux/actions'

class EditRepository extends Component {
  constructor(props) {
    super(props)
    this.state = {
      params: {},
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
    dispatch(repositoryActions.getConnectionParams(repositoryId))
  }

  handleChange = (name) => (event) => {
    this.setState({ params: { ...this.state.params, [name]: event.target.value } })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { dispatch, match } = this.props
    const repositoryId = match.params.id
    dispatch(repositoryActions.updateConnectionParams(repositoryId, this.state.params))
  }

  render() {
    const { loadedRepository, connectionParams, loadedConnectionParams } = this.props
    if (!loadedRepository) {
      return null
    }
    if (!loadedConnectionParams) {
      return null
    }
    const dynamicForm = connectionParams.map(param => {
      let input = null
      if (param.type === 'boolean') {
        input = (
          <div>
            <InputLabel htmlFor={`__${param.name}`}>{param.label}</InputLabel>
            <NativeSelect
              onChange={this.handleChange(param.name)}
              inputProps={{ id: `__${param.name}` }}
            >
              <option>Selecciona</option>
              <option value="true">Si</option>
              <option value="false">No</option>
            </NativeSelect>
          </div>)
      } else {
        input = (<TextField label={param.label} type={param.type} onChange={this.handleChange(param.name)} />)
      }
      return (
        <FormGroup key={param.name}>
          <FormControl>
            {input}
          </FormControl>
        </FormGroup >
      )
    })
    return (
      <form autoComplete="off" className="mddv-form">
        {dynamicForm}
        <FormGroup>
          <FormControl>
            <br />
            <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
              Actualizar Parámetros de Conexión
              <Save className="icon-button" />
            </Button>
          </FormControl>
        </FormGroup>
      </form>
    )
  }
}

const mapStateToProps = state => {
  const { loading, loadedRepository, loadedConnectionParams, connectionParams, repository } = state.repositoryReducer
  return { loading, loadedRepository, loadedConnectionParams, connectionParams, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(EditRepository)))
