import axios from 'axios'
import { Constants, Session } from '../utils'

const setAuthorizationToken = (config = {}) => {
  const token = Session.getToken()
  if (token) {
    return { headers: { ...config, Authorization: token } }
  }
  return config
}

const post = ({ endpoint = '', data, config = {} }) =>
  axios.post(`${Constants.BASE_URL}/${endpoint}`, data, setAuthorizationToken(config))

const get = ({ endpoint = '' }) =>
  axios.get(`${Constants.BASE_URL}/${endpoint}`, setAuthorizationToken())

const put = ({ endpoint = '', data, config = {} }) =>
  axios.put(`${Constants.BASE_URL}/${endpoint}`, data, setAuthorizationToken(config))

const del = ({ endpoint = '', config = {} }) =>
  axios.delete(`${Constants.BASE_URL}/${endpoint}`, setAuthorizationToken(config))

const httpClient = {
  post,
  get,
  put,
  delete: del,
}

export default httpClient
