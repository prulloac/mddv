import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Header, Image } from 'semantic-ui-react'
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
        <Header as="h1">Objetos de Negocio</Header>
        <p>This is a basic fixed menu template using fixed size containers.</p>
        <p>
          A text container is used for the main container,
          which is useful for single column layouts.
        </p>
        <Image src={img.mediaParagraph} style={{ marginTop: '2em' }} />
        <Image src={img.paragraph} style={{ marginTop: '2em' }} />
        <Image src={img.paragraph} style={{ marginTop: '2em' }} />
        <Image src={img.paragraph} style={{ marginTop: '2em' }} />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoryReducer
  return { repositories, repository }
}

export default connect(mapStateToProps)(BusinessObjects)
