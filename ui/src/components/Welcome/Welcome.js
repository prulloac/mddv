import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Paper, Typography } from '@material-ui/core'
import { title } from '../../utils/UtilityFunctions'

import './Welcome.scss'

class Welcome extends Component {
  constructor(props) {
    super(props)
    title()
  }

  render() {
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Bienvenido!</Typography>
        </Paper>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withRouter(connect(mapStateToProps)(Welcome))
