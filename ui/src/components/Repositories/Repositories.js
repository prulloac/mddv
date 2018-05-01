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

  componentWillMount() {
    this.setState({
      repositoryName: this.props.repository.name,
      repositoryType: this.props.repository.type,
      repositoryLocation: this.props.repository.location,
    })
  }

  handleRedirect = (event, { to }) => {
    const { dispatch, currentPage } = this.props
    if (currentPage !== to) {
      dispatch(navigationActions.goTo(to))
    }
    if (to === '/repositorios/list') {
      this.loadRepositories()
    }
    if (to.match(/\/repositorios\/edit\/\d/)) {
      this.loadRepository(to)
    }
    if (to.match(/\/repositorios\/delete\/\d/)) {
      this.deleteRepository(to)
    }
  }

  deleteRepository = (currentPage = '') => {
    const { dispatch } = this.props
    const repositoryId = currentPage.replace('/repositorios/delete/', '')
    dispatch(repositoryActions.delete(repositoryId))
  }

  loadRepositories = () => {
    const { dispatch } = this.props
    dispatch(repositoryActions.getAll())
  }

  loadRepository = (currentPage = '') => {
    const { dispatch } = this.props
    const repositoryId = currentPage.replace('/repositorios/edit/', '')
    dispatch(repositoryActions.findById(repositoryId))
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

  handleUpdate = (event) => {
    event.preventDefault()
    const { repositoryName, repositoryType, repositoryLocation } = this.state
    const { id } = this.props.repository
    const { dispatch } = this.props
    if (repositoryName && repositoryType && repositoryLocation) {
      dispatch(repositoryActions.update({
        id,
        name: repositoryName,
        location: repositoryLocation,
        type: repositoryType,
      }))
    }
  }

  render() {
    const { currentPage, repositories } = this.props
    const { repositoryName, repositoryType, repositoryLocation } = this.state
    const repositoryForm = (
      <Form onSubmit={this.handleFormSubmit}>
        <Form.Input label="Nombre" name="repositoryName" value={repositoryName} onChange={this.handleChange} />
        <Form.Input label="Tipo" name="repositoryType" value={repositoryType} onChange={this.handleChange} />
        <Form.Input label="Ubicación" name="repositoryLocation" value={repositoryLocation} onChange={this.handleChange} />
        <Button content="Actualizar Repositorio" onClick={this.handleUpdate} />
      </Form>
    )
    const repositoryList = repositories.map((repository_) =>
      ({
        header: repository_.name,
        meta: repository_.type,
        description: (
          <div>
            <Button basic color="green" to={`/repositorios/edit/${repository_.id}`} onClick={this.handleRedirect}>Ver</Button>
            <Button basic color="red" to={`/repositorios/delete/${repository_.id}`} onClick={this.handleRedirect}>Eliminar</Button>
          </div>
        ),
      }))
    const newRepositoryForm = (
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
        {(currentPage === '/repositorios/new') && (<div>{newRepositoryForm}</div>)}
        {(currentPage.match(/\/repositorios\/edit\/\d/)) && (<div>{repositoryForm}</div>)}
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
