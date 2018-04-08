import React, { Component } from 'react'
import { Redirect } from 'react-router'
import { Grid, Header, Image, Form, Segment, Button } from 'semantic-ui-react'
import { connect } from 'react-redux'
import img from 'utils/Img'
import userActions from '../../redux/actions/user.actions'

import './LoginPage.scss'

class ConnectedLoginPage extends Component {
  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      submitted: false,
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleChange = (event) => {
    const { name, value } = event.target
    this.setState({ [name]: value })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    this.setState({ submitted: true })
    const { username, password } = this.state
    const { dispatch } = this.props
    if (username && password) {
      dispatch(userActions.login(username, password))
    }
  }

  render() {
    const { from } = this.props.location.state || { from: '/' }
    const {
      username,
      password,
      submitted,
    } = this.state
    const { loggingIn, token } = this.props
    if (token) {
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
            <Form size="large" onSubmit={this.handleSubmit}>
              <Segment stacked>
                <Header as="h3" color="teal" textAlign="left">
                  Inicio de Sesión
                </Header>
                <Form.Input
                  fluid
                  icon="user"
                  iconPosition="left"
                  placeholder="Correo electrónico"
                  name="username"
                  onChange={this.handleChange}
                />
                {submitted && !username &&
                  <div className="help-block">Se necesita ingresar correo o nombre de usuario</div>
                }
                <Form.Input
                  fluid
                  icon="lock"
                  iconPosition="left"
                  placeholder="Contraseña"
                  type="password"
                  name="password"
                  onChange={this.handleChange}
                />
                {submitted && !password &&
                  <div className="help-block">Se necesita ingresar correo o nombre de usuario</div>
                }
                <Button color="teal" fluid size="large" onClick={this.handleLoginForm}>Iniciar Sesión</Button>
                {loggingIn &&
                  <img alt="" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
                }
              </Segment>
            </Form>
          </Grid.Column>
        </Grid>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading, token } = state.userReducer
  return { loading, token }
}

const LoginPage = connect(mapStateToProps)(ConnectedLoginPage)

export default LoginPage
