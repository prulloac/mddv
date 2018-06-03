import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Dashboard from '../Dashboard/Dashboard'
import LoginPage from '../LoginPage/LoginPage'
import Notification from '../Notification/Notification'
import actions from '../../redux/actions/actions'
import { title, Session } from '../../utils'
import './App.scss'


class App extends Component {
  constructor(props) {
    super(props)
    if (Session.checkToken()) {
      this.props.dispatch(actions.userActions.validateUser())
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
