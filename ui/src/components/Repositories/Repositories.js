import React, { Component } from 'react'
import { Switch, Route, Link, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { Button, Typography, withStyles, Paper } from '@material-ui/core'
import { repositoryActions } from '../../redux/actions'
import NewRepositoryForm from './NewRepositoryForm'
import RepositoryList from './RepositoryList'
import EditRepository from './EditRepository'
import { title } from '../../utils'
import './Repositories.scss'

class Repositories extends Component {
  constructor(props) {
    super(props)
    title('Repositorios')
  }

  deleteRepository = (currentPage = '') => {
    const { dispatch } = this.props
    const repositoryId = currentPage.replace('/repositorios/delete/', '')
    dispatch(repositoryActions.delete(repositoryId))
  }

  render() {
    const { match } = this.props
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Repositorios</Typography>
        </Paper>
        <div className="mddv-buttons">
          <Button variant="raised" component={Link} to={`${match.path}/list`}>
            Ver repositorios registrados
          </Button>
          <Button variant="raised" component={Link} to="/repository/new">
            Registrar nuevo repositorio
          </Button>
        </div>
        <Switch>
          <Route exact path="/repository/new" component={NewRepositoryForm} />
          <Route exact path="/repository/list" component={RepositoryList} />
          <Route path="/repository/edit/:id" component={EditRepository} />
        </Switch>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository, extractableTypes } = state.repositoryReducer
  return { repositories, repository, extractableTypes }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(Repositories)))
