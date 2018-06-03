import jwt from 'jwt-decode'
import Constants from '../Constants/Constants'

const checkToken = () => {
  const token = localStorage.getItem(Constants.TOKEN_NAME)
  if (token) {
    const decoded = jwt(token.replace('Bearer ', ''))
    if (decoded.username) {
      return true
    }
  }
  return false
}

const getUserData = () => {
  if (checkToken()) {
    return jwt(localStorage.getItem(Constants.TOKEN_NAME).replace('Bearer ', ''))
  }
  return null
}

const logOut = () => {
  localStorage.removeItem(Constants.TOKEN_NAME)
}

const setSession = (token = '') => {
  localStorage.setItem(Constants.TOKEN_NAME, token)
}

export default {
  checkToken,
  logOut,
  setSession,
  getUserData,
}
