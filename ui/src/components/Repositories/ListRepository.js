import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import { Card, Button, CardContent, Typography, CardActions, Icon, Badge } from '@material-ui/core'
import { repositoryActions } from '../../redux/actions'

class RepositoryList extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(repositoryActions.getExtractorCompatibles())
    this.props.dispatch(repositoryActions.resetRepository())
    this.props.dispatch(repositoryActions.getAll())
  }

  handleDelete = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch } = this.props
    if (id > 0) {
      dispatch(repositoryActions.delete(id))
      dispatch(repositoryActions.getAll())
    }
  }

  handleTestConnection = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch } = this.props
    if (id > 0) {
      dispatch(repositoryActions.testRepository(id))
    }
  }

  render() {
    const newButton = (
      <div className="mddv-buttons" align="right">
        <Button variant="raised" component={Link} to="/repository/new">
          Registrar nuevo repositorio
        </Button>
      </div>
    )
    const { loadedRepositories, repositories } = this.props
    if (!loadedRepositories) {
      return newButton
    }
    const repositoryCards = repositories.map((repository) => (
      <Card className="mddv-repo-card" key={repository.id}>
        <CardContent>
          {repository.hasConnectionParams ?
            <Button
              mini
              variant="fab"
              color="primary"
              style={{ color: '#fff', marginTop: '0px', marginRight: '0px', position: 'relative', float: 'right' }}
              onClick={this.handleTestConnection(repository.id)}
            >
              <Icon>sync_problem</Icon>
            </Button> : null}
          <Typography variant="headline">{repository.name}</Typography>
          <Typography>Tipo: {repository.engine} ({repository.version || 'Sin Versión'})</Typography>
          <Typography>Ubicación: {repository.location} <Icon>{repository.outsourced ? 'cloud' : 'business' }</Icon></Typography>
        </CardContent>
        <CardActions>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/repository/edit/${repository.id}`}
          >
            Editar
            <Icon className="icon-button">edit</Icon>
          </Button>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/repository/params/${repository.id}`}
            style={{ color: '#fff' }}
          >
            Conexión
            {repository.hasConnectionParams ?
              (<Icon className="icon-button">security</Icon>) :
              (<Badge badgeContent="" color="error" className="icon-button"><Icon>security</Icon></Badge>)
            }
          </Button>
          <Button
            size="small"
            variant="raised"
            color="secondary"
            onClick={this.handleDelete(repository.id)}
          >
            Eliminar
            <Icon className="icon-button">delete</Icon>
          </Button>
        </CardActions>
      </Card>
    ))
    return (
      <div>{newButton}{repositoryCards}</div>
    )
  }
}

const mapStateToProps = state => {
  const { loadedRepositories, repositories } = state.repositoryReducer
  return { loadedRepositories, repositories }
}

export default withRouter(connect(mapStateToProps)(RepositoryList))
