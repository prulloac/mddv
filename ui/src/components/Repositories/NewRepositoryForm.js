import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Input, Button } from 'react-materialize'
import repositoryActions from '../../redux/actions/repository-actions'

class NewRepositoryForm extends Component {
  constructor(props) {
    super(props)
    this.state = {
      name: null,
      type: null,
      location: null,
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleChange = (event) => {
    const { name, value } = event.target
    this.setState({ [name]: value })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { name, type, location } = this.state
    const { dispatch } = this.props
    if (name && type && location) {
      console.log('hey!')
      dispatch(repositoryActions.create({ location, name, type }))
    }
  }

  render() {
    const { extractableEngines, loadedExtractables } = this.props
    const selectOptions = extractableEngines.map(x => (<option key={x.key} value={x.value}>{x.text}</option>))
    if (!loadedExtractables) {
      return null
    }
    return (
      <form>
        <Input label="Nombre" name="name" onChange={this.handleChange} />
        <Input type="select" label="Tipo" name="type" onChange={this.handleChange}>
          <option>Selecciona motor</option>
          {selectOptions}
        </Input>
        <Input label="Ubicación" name="location" onChange={this.handleChange} />
        <Button onClick={this.handleSubmit}>Registrar Repositorio</Button>
      </form>
    )
  }
}

const mapStateToProps = state => {
  const { loadedExtractables, extractableEngines } = state.repositoryReducer
  return { loadedExtractables, extractableEngines }
}

export default withRouter(connect(mapStateToProps)(NewRepositoryForm))
