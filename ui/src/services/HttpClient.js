import axios from 'axios'
import { BASE_URL } from '../utils/Constants'

const post = ({ endpoint = '', data = '', config = {} }) =>
  axios.post(`${BASE_URL}/${endpoint}`, data, config)

const get = ({ endpoint = '' }) =>
  axios.get(`${BASE_URL}/${endpoint}`)

const put = ({ endpoint = '', data = '', config = {} }) =>
  axios.put(`${BASE_URL}/${endpoint}`, data, config)

const del = ({ endpoint = '', config = {} }) =>
  axios.delete(`${BASE_URL}/${endpoint}`, config)

const auth = (token = '') => ({ headers: { Authorization: token } })

const httpClient = {
  post,
  get,
  put,
  delete: del,
  auth,
}

export default httpClient
