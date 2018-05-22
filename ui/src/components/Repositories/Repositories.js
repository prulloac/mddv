import React, { Component } from 'react'
import { Switch, Route, Link, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { Button } from 'react-materialize'
import repositoryActions from '../../redux/actions/repository-actions'
import NewRepositoryForm from './NewRepositoryForm'
import RepositoryList from './RepositoryList'
import EditRepository from './EditRepository'
import './Repositories.scss'

class Repositories extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  deleteRepository = (currentPage = '') => {
    const { dispatch } = this.props
    const repositoryId = currentPage.replace('/repositorios/delete/', '')
    dispatch(repositoryActions.delete(repositoryId))
  }

  render() {
    const { currentPage, extractableEngines } = this.props
    const { repositoryName, repositoryType, repositoryLocation } = this.state
    const repositoryForm = (repositoryName && repositoryType && extractableEngines) ?
      this.editRepositoryForm(repositoryName, extractableEngines, repositoryType, repositoryLocation) : null
    return (
      <div>
        <div>
          <h1>Repositorios</h1>
          <Button waves="light" node={Link} to="/repository/list">Ver repositorios registrados</Button>
          <Button waves="light" node={Link} to="/repository/new">Registrar nuevo repositorio</Button>
        </div>
        <Switch>
          <Route exact path="/repository/new" component={NewRepositoryForm} />
          <Route exact path="/repository/list" component={RepositoryList} />
          <Route path="/repository/edit/:id" component={EditRepository} />
        </Switch>
        {(currentPage.match(/\/repositorios\/edit\/\d/)) && (<div>{repositoryForm}</div>)}
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository, extractableTypes } = state.repositoryReducer
  const { navigationHistory, currentPage } = state.navigationReducer
  return { repositories, repository, extractableTypes, navigationHistory, currentPage }
}

export default withRouter(connect(mapStateToProps)(Repositories))
