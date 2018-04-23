import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Header, Image } from 'semantic-ui-react'
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
        <Header as="h1">Bienvenido!</Header>
        <Image src={img.mediaParagraph} style={{ marginTop: '2em' }} />
        <Image src={img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default connect(mapStateToProps)(Welcome)
