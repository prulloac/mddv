import UserService from '../../services/UserService'
import userActionTypes from '../action-types/user-action-types'
import notificationActions from './notification-actions'
import Session from '../../utils/Session/Session'

const login = (username = '', password = '') => {
  const request = () => ({ type: userActionTypes.FETCH_TOKEN, payload: null })
  const success = () => ({ type: userActionTypes.FETCH_TOKEN_SUCCESS, payload: null })
  const failure = (error) => ({ type: userActionTypes.FETCH_TOKEN_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    UserService.login(username, password).then(
      response => {
        Session.setSession(response.headers.authorization)
        dispatch(success())
        dispatch(notificationActions.notify('Sesión iniciada'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Error al iniciar sesión'))
      },
    )
  }
}

const logout = () => {
  const resetToken = () => ({ type: userActionTypes.TOKEN_RESET, payload: null })
  return dispatch => {
    Session.logOut()
    dispatch(resetToken())
    dispatch(notificationActions.notify('Sesión finalizada'))
  }
}

const validateUser = () => ({ type: userActionTypes.FETCH_TOKEN_SUCCESS, payload: null })

const userActions = {
  login,
  logout,
  validateUser,
}

export default userActions
