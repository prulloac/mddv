import React from 'react'
import { Route } from 'react-router-dom'
import LoginPage from 'components/LoginPage'
import Dashboard from 'components/Dashboard'
import FakeAuth from 'utils/FakeAuth'

import './App.scss'

const App = () => (
  FakeAuth.isAuthenticated ? (
    <Route component={Dashboard} />
  ) : (
    <Route component={LoginPage} />
  )
)

export default App
