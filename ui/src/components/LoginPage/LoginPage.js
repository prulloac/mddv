import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Paper, FormGroup, FormControl, TextField, Typography, Button, Icon } from '@material-ui/core'
import actions from '../../redux/actions/actions'
import { Img } from '../../utils'
import './LoginPage.scss'

class LoginPage extends Component {
  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    document.title = 'Iniciar Sesión'
  }

  handleChange = (name) => (event) => {
    this.setState({ [name]: event.target.value })
  }

  handleSubmit = (event) => {
    event.preventDefault()
    const { username, password } = this.state
    const { dispatch } = this.props
    if (username && password) {
      dispatch(actions.userActions.login(username, password))
    }
  }

  render() {
    const { username, password } = this.state
    return (
      <div>
        <Paper elevation={4} className="login-card">
          <Typography variant="title" align="center">
            <img src={Img.mddvLogo} alt="mddv-logo" style={{ width: '50px' }} />
            <br />
            Mddv - Iniciar Sesión
          </Typography>
          <form autoComplete="no" className="login-form">
            <FormGroup>
              <FormControl>
                <TextField label="Usuario" value={username} onChange={this.handleChange('username')} />
              </FormControl>
            </FormGroup>
            <FormGroup>
              <FormControl>
                <TextField label="Contraseña" type="password" value={password} onChange={this.handleChange('password')} />
              </FormControl>
            </FormGroup>
            <FormGroup>
              <FormControl>
                <br />
                <Button size="small" variant="raised" onClick={this.handleSubmit} color="primary">
                  Iniciar Sesión
                  <Icon className="icon-button">account_circle</Icon>
                </Button>
              </FormControl>
            </FormGroup>
          </form>
        </Paper>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading } = state.userReducer
  return { loading }
}

export default connect(mapStateToProps)(LoginPage)
