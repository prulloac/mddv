import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import LoginPage from 'components/LoginPage'
import Dashboard from 'components/Dashboard'
import FakeAuth from 'utils/FakeAuth'

import './App.scss'

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props => (
    FakeAuth.isAuthenticated() ? (
      <Component {...props} />
    ) : (
      <Redirect
        to={{
          pathname: '/login',
          state: { from: props.location },
        }}
      />
    ))}
  />
)

const App = () => (
  <div>
    <Route path="/login" component={LoginPage} />
    <PrivateRoute path="/dashboard" component={Dashboard} />
    <Redirect from="/" to={FakeAuth.isAuthenticated() ? '/dashboard' : '/login'} />
  )
  </div>
)

export default App
