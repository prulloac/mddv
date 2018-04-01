import React, { Component } from 'react'
import { Redirect } from 'react-router'
import { Grid, Header, Image, Form, Segment, Button } from 'semantic-ui-react'
import img from 'utils/Img'
import FakeAuth from 'utils/FakeAuth/FakeAuth'

import './LoginPage.scss'

class LoginPage extends Component {
  state = {
    username: '',
    redirectToReferrer: false,
  }

  login = () => {
    FakeAuth.authenticate(() => (
      this.setState({ redirectToReferrer: true })
    ))
  }

  handleLoginForm = (event) => {
    event.preventDefault()
    FakeAuth.setUser(this.state.username)
    this.login()
  }

  handleUsername = (event) => {
    this.setState({ username: event.target.value })
  }

  render() {
    const { from } = this.props.location.state || { from: '/' }
    const { redirectToReferrer } = this.state
    if (redirectToReferrer) {
      return (
        <Redirect to={from} />
      )
    }

    return (
      <div className="login-form">
        <Grid
          textAlign="center"
          style={{ height: '100%' }}
          verticalAlign="middle"
          >
          <Grid.Column style={{ maxWidth: 450 }}>
            <Header as="h2" color="teal" textAlign="center">
              <Image src={img.mddvLogo} />
              MDDV
            </Header>
            <Form size="large">
              <Segment stacked>
                <Header as="h3" color="teal" textAlign="left">
                  Inicio de Sesión
                </Header>
                <Form.Input
                  fluid
                  icon="user"
                  iconPosition="left"
                  placeholder="Correo electrónico"
                  onChange={this.handleUsername}
                />
                <Form.Input
                  fluid
                  icon="lock"
                  iconPosition="left"
                  placeholder="Contraseña"
                  type="password"
                />
                <Button color="teal" fluid size="large" onClick={this.handleLoginForm}>Iniciar Sesión</Button>
              </Segment>
            </Form>
          </Grid.Column>
        </Grid>
      </div>
    )
  }
}

export default LoginPage
