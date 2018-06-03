import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Paper, Typography } from '@material-ui/core'
import { title, Img } from '../../utils'

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
        <img alt="paragraph" src={Img.mediaParagraph} style={{ marginTop: '2em' }} />
        <img alt="paragraph" src={Img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withRouter(connect(mapStateToProps)(Welcome))
