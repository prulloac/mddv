import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Header, Button, Segment, Form, Card } from 'semantic-ui-react'
import navigationActions from '../../redux/actions/navigation-actions'
import repositoryActions from '../../redux/actions/repository-actions'
import './Repositories.scss'

class Repositories extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.handleRedirect = this.handleRedirect.bind(this)
    this.loadRepositories = this.loadRepositories.bind(this)
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleRedirect = (event, { to }) => {
    const { dispatch, currentPage } = this.props
    if (currentPage !== to) {
      dispatch(navigationActions.goTo(to))
    }
    if (to === '/repositorios/list') {
      this.loadRepositories()
    }
  }

  loadRepositories = () => {
    const { dispatch } = this.props
    dispatch(repositoryActions.getAll())
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
      dispatch(repositoryActions.create({ location, name, type }))
    }
  }

  render() {
    const { currentPage, repositories } = this.props
    const repositoryList = repositories.map((repository) =>
      ({
        header: repository.name,
        meta: repository.type,
        description: (
          <div>
            <Button basic color="green" to={`/repositorios/edit/${repository.id}`} onClick={this.handleRedirect}>Ver</Button>
            <Button basic color="red" to={`/repositorios/delete/${repository.id}`} onClick={this.handleRedirect}>Eliminar</Button>
          </div>
        ),
      }))
    const repositoryForm = (
      <Form onSubmit={this.handleFormSubmit}>
        <Form.Input label="Nombre" name="name" onChange={this.handleChange} />
        <Form.Input label="Tipo" name="type" onChange={this.handleChange} />
        <Form.Input label="Ubicación" name="location" onChange={this.handleChange} />
        <Button content="Registrar Repositorio" onClick={this.handleSubmit} />
      </Form>
    )
    return (
      <div>
        <Segment piled>
          <Header as="h1">Repositorios</Header>
          <Button content="Ver repositorios registrados" size="large" to="/repositorios/list" onClick={this.handleRedirect} />
          <Button content="Registrar nuevo repositorio" size="large" to="/repositorios/new" onClick={this.handleRedirect} />
        </Segment>
        {(currentPage === '/repositorios/list') && (<Card.Group items={repositoryList} />)}
        {(currentPage === '/repositorios/new') && (<div>{repositoryForm}</div>)}
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  const { navigationHistory, currentPage } = state.navigationReducer
  return { repositories, repository, navigationHistory, currentPage }
}

export default connect(mapStateToProps)(Repositories)
