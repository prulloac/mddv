import React, { Component } from 'react'
import { HashRouter, Route } from 'react-router-dom'
import LoginPage from 'components/LoginPage'
import Dashboard from 'components/Dashboard'
import { connect } from 'react-redux'
import PrivateRoute from '../PrivateRoute'

import './App.scss'

class ConnectedApp extends Component {
  render() {
    const { isAuthenticated } = this.props
    return (
      <HashRouter>
        <div>
          <Route path="/login" component={LoginPage} />
          <PrivateRoute exact path="/" auth={isAuthenticated} component={Dashboard} />
        </div>
      </HashRouter>)
  }
}

const mapStateToProps = state => {
  const { isAuthenticated } = state.userReducer
  return { isAuthenticated }
}

const App = connect(mapStateToProps)(ConnectedApp)

export default App
