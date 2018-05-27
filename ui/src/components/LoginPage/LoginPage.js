import React, { Component } from 'react'
import { connect } from 'react-redux'
import img from 'utils/Img'
import userActions from '../../redux/actions/user-actions'
import navigationActions from '../../redux/actions/navigation-actions'

import './LoginPage.scss'

class LoginPage extends Component {
  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      submitted: false,
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.props.dispatch(navigationActions.goTo('login'))
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
    const {
      username,
      password,
      submitted,
    } = this.state
    const { loggingIn } = this.props
    return (
      <div className="login-form">
        nothing
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading, token } = state.userReducer
  const { navigationHistory } = state.navigationReducer
  return { loading, token, navigationHistory }
}

export default connect(mapStateToProps)(LoginPage)
