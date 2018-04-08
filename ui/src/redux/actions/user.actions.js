import service from '../../services/UserService'
import userActionsTypes from '../action-types/user-action-types'

const login = (username = '', password = '') => {
  const request = (user) => ({ type: userActionsTypes.FETCH_TOKEN, payload: user })
  const success = (token) => ({ type: userActionsTypes.FETCH_TOKEN_SUCCESS, payload: token })
  const failure = (error) => ({ type: userActionsTypes.FETCH_TOKEN_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ username }))
    service.login(username, password)
      .then(
        response => dispatch(success(response.headers.authorization)),
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
