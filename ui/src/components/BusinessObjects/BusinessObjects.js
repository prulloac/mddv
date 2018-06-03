import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography, Paper } from '@material-ui/core'
import { title, Img } from '../../utils'

import './BusinessObjects.scss'

class BusinessObjects extends Component {
  constructor(props) {
    super(props)
    title('Objetos de Negocio')
  }

  render() {
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Objetos de Negocio</Typography>
        </Paper>
        <p>This is a basic fixed menu template using fixed size containers.</p>
        <p>
          A text container is used for the main container,
          which is useful for single column layouts.
        </p>
        <img alt="" src={Img.mediaParagraph} style={{ marginTop: '2em' }} />
        <img alt="" src={Img.paragraph} style={{ marginTop: '2em' }} />
        <img alt="" src={Img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(BusinessObjects)))
