import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import img from 'utils/Img'

import './Welcome.scss'

class Welcome extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  render() {
    return (
      <div>
        <h1>Bienvenido!</h1>
        <img alt="paragraph" src={img.mediaParagraph} style={{ marginTop: '2em' }} />
        <img alt="paragraph" src={img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default withRouter(connect(mapStateToProps)(Welcome))
