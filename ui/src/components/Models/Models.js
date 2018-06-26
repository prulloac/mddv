import React, { Component } from 'react'
import { withRouter, Switch, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography, Paper, Button } from '@material-ui/core'
import { title } from '../../utils'
import './Models.scss'
import FreeModel from './FreeModel'
import RepositoryModel from './RepositoryModel'

class Models extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    title('Modelos')
  }

  render() {
    const { match } = this.props
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Modelos</Typography>
        </Paper>
        <Button
          mini
          variant="raised"
          color="primary"
          style={{ color: '#fff', marginTop: '0px', marginRight: '0px', position: 'relative', float: 'right' }}
          component={Link}
          to={`${match.path}/free`}
        >
          Nuevo Modelo Libre
        </Button>
        <div />
        <Button
          mini
          variant="raised"
          color="primary"
          style={{ color: '#fff', marginTop: '0px', marginRight: '0px', position: 'relative', float: 'right' }}
          component={Link}
          to={`${match.path}/repository`}
        >
          Nuevo Modelo De Repositorio
        </Button>
        <Switch>
          <Route exact path={`${match.path}/free`} component={FreeModel} />
          <Route exact path={`${match.path}/repository`} component={RepositoryModel} />
        </Switch>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(Models)))
