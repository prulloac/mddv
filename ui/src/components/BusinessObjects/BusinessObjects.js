import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography } from '@material-ui/core'
import img from 'utils/Img'

import './BusinessObjects.scss'

class BusinessObjects extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  render() {
    return (
      <div>
        <Typography variant="headline">Objetos de Negocio</Typography>
        <p>This is a basic fixed menu template using fixed size containers.</p>
        <p>
          A text container is used for the main container,
          which is useful for single column layouts.
        </p>
        <img alt="" src={img.mediaParagraph} style={{ marginTop: '2em' }} />
        <img alt="" src={img.paragraph} style={{ marginTop: '2em' }} />
        <img alt="" src={img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(BusinessObjects)))
