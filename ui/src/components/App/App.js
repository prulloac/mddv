import React, { Component } from 'react'
// import LoginPage from 'components/LoginPage'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Dashboard from '../Dashboard'

import './App.scss'

class App extends Component {
  render() {
    const { isAuthenticated } = this.props
    if (isAuthenticated) {
      return (null)
    }
    return (
      <Dashboard />
    )
  }
}

const mapStateToProps = state => {
  const { isAuthenticated } = state.userReducer
  return { isAuthenticated }
}

export default withRouter(connect(mapStateToProps)(App))
