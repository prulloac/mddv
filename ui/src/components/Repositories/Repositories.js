import React, { Component } from 'react'
import { Switch, Route, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { Typography, withStyles, Paper } from '@material-ui/core'
import { repositoryActions } from '../../redux/actions'
import New from './NewRepository'
import List from './ListRepository'
import Edit from './EditRepository'
import EditParams from './EditParams'
import { title } from '../../utils'
import './Repositories.scss'

class Repositories extends Component {
  constructor(props) {
    super(props)
    title('Repositorios')
    this.props.dispatch(repositoryActions.getExtractorCompatibles())
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
        <Switch>
          <Route exact path="/repository" component={List} />
          <Route exact path={`${match.path}/new`} component={New} />
          <Route path={`${match.path}/edit/:id`} component={Edit} />
          <Route path={`${match.path}/params/:id`} component={EditParams} />
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
