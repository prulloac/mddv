import axios from 'axios'
import { BASE_URL } from '../../utils/Constants'

const post = ({ endpoint = '', data = '', config = {} }) =>
  axios.post(`${BASE_URL}/users/${endpoint}`, data, config)

const get = ({ endpoint = '' }) =>
  axios.get(`${BASE_URL}/users/${endpoint}`)

const put = ({ endpoint = '', data = '', config = {} }) =>
  axios.put(`${BASE_URL}/users/${endpoint}`, data, config)

const del = ({ endpoint = '', config = {} }) =>
  axios.delete(`${BASE_URL}/users/${endpoint}`, config)

const login = (username = '', password = '') =>
  post({ endpoint: 'login', data: { usernameOrEmail: username, password } })

const service = {
  post,
  get,
  put,
  delete: del,
  login,
}

export default service
