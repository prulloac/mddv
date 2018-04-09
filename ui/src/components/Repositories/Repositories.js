import React, { Component } from 'react'
import { connect } from 'react-redux'

import './Repositories.scss'

class ConnectedRepositories extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.logout = this.logout.bind(this)
    this.toggleSidebarVisibility = this.toggleSidebarVisibility.bind(this)
  }

  render() {
    return (
      <div>
        something
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.repositoriesReducer
  return { repositories, repository }
}

const Repositories = connect(mapStateToProps)(ConnectedRepositories)
export default Repositories
