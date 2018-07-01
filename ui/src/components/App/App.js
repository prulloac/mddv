import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Dashboard from '../Dashboard/Dashboard'
import LoginPage from '../LoginPage/LoginPage'
import Notification from '../Notification/Notification'
import userActions from '../../redux/actions/user-actions'
import { title } from '../../utils/UtilityFunctions'
import Session from '../../utils/Session'
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
