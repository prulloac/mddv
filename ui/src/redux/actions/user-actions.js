import UserService from '../../services/UserService'
import userActionTypes from '../action-types/user-action-types'

const login = (username = '', password = '') => {
  const request = (user) => ({ type: userActionTypes.FETCH_TOKEN, payload: user })
  const success = (token, user) => ({
    type: userActionTypes.FETCH_TOKEN_SUCCESS,
    payload: { token, user },
  })
  const failure = (error) => ({ type: userActionTypes.FETCH_TOKEN_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ username }))
    UserService.login(username, password).then(
      response => dispatch(success(response.headers.authorization, response.data)),
      error => dispatch(failure(error)),
    )
  }
}

const logout = () => ({ type: userActionTypes.TOKEN_RESET, payload: '' })

const findById = (id = 0) => {
  const request = (user) => ({ type: userActionTypes.R_USER, payload: user })
  const success = (user) => ({ type: userActionTypes.R_USER_SUCCESS, payload: user })
  const failure = (error) => ({ type: userActionTypes.R_USER_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    UserService.findById(id).then(
      response => dispatch(success(response.data)),
      error => dispatch(failure(error)),
    )
  }
}

const findByName = (name = '') => {
  const request = (user) => ({ type: userActionTypes.R_USER, payload: user })
  const success = (user) => ({ type: userActionTypes.R_USER_SUCCESS, payload: user })
  const failure = (error) => ({ type: userActionTypes.R_USER_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    UserService.findByName(name).then(
      response => dispatch(success(response.data)),
      error => dispatch(failure(error)),
    )
  }
}

const findByUsername = (username = '') => {
  const request = (user) => ({ type: userActionTypes.R_USER, payload: user })
  const success = (user) => ({ type: userActionTypes.R_USER_SUCCESS, payload: user })
  const failure = (error) => ({ type: userActionTypes.R_USER_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(username))
    UserService.findByUsername(username).then(
      response => dispatch(success(response.data)),
      error => dispatch(failure(error)),
    )
  }
}

const userActions = {
  login,
  logout,
  findById,
  findByName,
  findByUsername,
}

export default userActions
