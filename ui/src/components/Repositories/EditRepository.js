import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Input, Button } from 'react-materialize'
import repositoryActions from '../../redux/actions/repository-actions'

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

  handleChange = (event) => {
    const { name, value } = event.target
    this.setState({ [name]: value })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { name, type, location } = this.state
    const { dispatch, match } = this.props
    if (name && type && location) {
      dispatch(repositoryActions.update({ id: match.params.id, name, location, type }))
    }
  }

  render() {
    const { loadedRepository, extractableEngines, repository } = this.props
    if (!loadedRepository) {
      return null
    }
    const selectOptions = extractableEngines.map(x => (
      <option key={x.key} value={x.value}>{x.text}</option>
    ))
    return (
      <form onSubmit={this.handleFormSubmit}>
        <Input label="Nombre" name="name" defaultValue={repository.name} onChange={this.handleChange} />
        <Input type="select" label="Tipo" name="type" defaultValue={repository.type} onChange={this.handleChange}>
          <option>Selecciona motor</option>
          {selectOptions}
        </Input>
        <Input label="Ubicación" name="location" defaultValue={repository.location} onChange={this.handleChange} />
        <Button content="Actualizar Repositorio" onClick={this.handleUpdate} />
      </form>
    )
  }
}

const mapStateToProps = state => {
  const { loading, loadedRepository, loadedExtractables, extractableEngines, repository } = state.repositoryReducer
  return { loading, loadedRepository, loadedExtractables, extractableEngines, repository }
}

export default withRouter(connect(mapStateToProps)(EditRepository))
