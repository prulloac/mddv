import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Dashboard, LoginPage, Notification } from '../index'
import { userActions } from '../../redux/actions'
import { title, Session } from '../../utils'
import './App.scss'

class App extends Component {
  constructor(props) {
    super(props)
    if (Session.checkToken()) {
      this.props.dispatch(userActions.validateUser())
    }
    title()
  }
  render() {
    const { isAuthenticated } = this.props
    if (!isAuthenticated) {
      return (
        <div>
          <LoginPage />
          <Notification />
        </div>
      )
    }
    return (
      <div>
        <Dashboard />
        <Notification />
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { isAuthenticated } = state.userReducer
  return { isAuthenticated }
}

export default withRouter(connect(mapStateToProps)(App))
