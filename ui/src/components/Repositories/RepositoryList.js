import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import { Card, Button, CardContent, Typography, CardActions, Icon, MuiThemeProvider, colors, createMuiTheme } from '@material-ui/core'
import { repositoryActions } from '../../redux/actions'

class RepositoryList extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
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

  render() {
    const { loadedRepositories, repositories } = this.props
    if (!loadedRepositories) {
      return null
    }
    const repositoryCards = repositories.map((repository) => (
      <Card className="mddv-repo-card" key={repository.id}>
        <CardContent>
          <Typography variant="headline">{repository.name}</Typography>
          <Typography>Tipo: {repository.type} ({repository.version || 'Sin Versión'})</Typography>
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
            color="secondary"
            onClick={this.handleDelete(repository.id)}
          >
            Eliminar
            <Icon className="icon-button">delete</Icon>
          </Button>
          <MuiThemeProvider theme={createMuiTheme({ palette: { primary: colors.green } })}>
            <Button
              size="small"
              variant="raised"
              color="primary"
              component={Link}
              to={`/repository/params/${repository.id}`}
              style={{ color: '#fff' }}
            >
              Conexión
              <Icon className="icon-button">security</Icon>
            </Button>
          </MuiThemeProvider>
        </CardActions>
      </Card>
    ))
    return (
      <div>{repositoryCards}</div>
    )
  }
}

const mapStateToProps = state => {
  const { loadedRepositories, repositories } = state.repositoryReducer
  return { loadedRepositories, repositories }
}

export default withRouter(connect(mapStateToProps)(RepositoryList))
