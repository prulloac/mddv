import UserService from '../../services/UserService'
import userActionsTypes from '../action-types/user-action-types'

const login = (username = '', password = '') => {
  const request = (user) => ({ type: userActionsTypes.FETCH_TOKEN, payload: user })
  const success = (token, user) => ({
    type: userActionsTypes.FETCH_TOKEN_SUCCESS,
    payload: {
      token,
      user,
    },
  })
  const failure = (error) => ({ type: userActionsTypes.FETCH_TOKEN_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ username }))
    UserService.login(username, password)
      .then(
        response => dispatch(success(response.headers.authorization, response.data)),
        error => dispatch(failure(error)),
      )
  }
}

const logout = () => ({ type: userActionsTypes.TOKEN_RESET, payload: '' })

const userActions = {
  login,
  logout,
}

export default userActions
