import React, { Component } from 'react'
import { Switch, Route, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { Typography, withStyles, Paper } from '@material-ui/core'
import New from './NewTechnicalObject'
import List from './ListTechnicalObject'
import Edit from './EditTechnicalObject'
import { title } from '../../utils/UtilityFunctions'
import './TechnicalObjects.scss'

class TechnicalObjects extends Component {
  constructor(props) {
    super(props)
    title('Objetos Técnicos')
  }

  render() {
    const { match } = this.props
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Objetos Técnicos</Typography>
        </Paper>
        <Switch>
          <Route path="/technical/:id?" component={List} />
          <Route exact path={`${match.path}/new`} component={New} />
          <Route path={`${match.path}/edit/:id`} component={Edit} />
        </Switch>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository, extractableTypes } = state.repositoryReducer
  return { repositories, repository, extractableTypes }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(TechnicalObjects)))
