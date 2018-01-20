import React, { Fragment } from 'react'
import { Route } from 'react-router-dom'
import { Navbar } from 'react-materialize'

import Dashboard from 'components/Dashboard'
import NotFound from 'components/NotFound'
import FakeAuth from 'utils/FakeAuth'
import LoginForm from 'components/LoginForm'

import './App.scss'

const App = () => {
  const logo = 'Mddv'
  const NavProfile = <Fragment>{FakeAuth.username}</Fragment>
  const NavLogin = <LoginForm />
  return (
    <div>
      <Navbar brand={logo} className="teal lighten-2" right>
        {FakeAuth.isAuthenticated ? NavProfile : NavLogin}
      </Navbar>
      <Route path="/dashboard" component={Dashboard} />
      <Route component={NotFound} />
    </div>
  )
}

export default App
