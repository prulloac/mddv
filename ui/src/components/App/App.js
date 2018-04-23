import React from 'react'
import { HashRouter } from 'react-router-dom'
import LoginPage from 'components/LoginPage'
import Dashboard from 'components/Dashboard'
import { connect } from 'react-redux'

import './App.scss'

const App = (props) => {
  const { isAuthenticated } = props
  if (!isAuthenticated) {
    return (<LoginPage />)
  }
  return (<HashRouter><Dashboard /></HashRouter>)
}

const mapStateToProps = state => {
  const { isAuthenticated } = state.userReducer
  return { isAuthenticated }
}

export default connect(mapStateToProps)(App)
