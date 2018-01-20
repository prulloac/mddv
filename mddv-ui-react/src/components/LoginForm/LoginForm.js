import React, { Component } from 'react'
import { Input, Button } from 'react-materialize'

import FakeAuth from 'utils/FakeAuth'

import './LoginForm.scss'

class LoginForm extends Component {
  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
    }
    this.handleUsername = this.handleUsername.bind(this)
    this.handlePassword = this.handlePassword.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleUsername(event) {
    this.setState({ username: event.target.value })
  }

  handlePassword(event) {
    this.setState({ password: event.target.value })
  }

  handleSubmit(event) {
    console.log(this.state.username)
    event.preventDeafult()
    FakeAuth.authenticate(null, this.state.username)
  }

  render() {
    return (
      <form>
        <Input
          s={2}
          label="Usuario"
          value={this.state.username}
          onChange={this.handleUsername}
          validate
        />
        <Input
          s={2}
          label="Contraseña"
          value={this.state.password}
          onChange={this.handlePassword}
        />
        <Button onClick={this.handleSubmit}>Iniciar Sesión</Button>
      </form>
    )
  }
}

export default LoginForm
