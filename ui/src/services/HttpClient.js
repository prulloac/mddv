import axios from 'axios'
import { Constants } from '../utils'

const post = ({ endpoint = '', data = '', config = {} }) =>
  axios.post(`${Constants.BASE_URL}/${endpoint}`, data, config)

const get = ({ endpoint = '' }) =>
  axios.get(`${Constants.BASE_URL}/${endpoint}`)

const put = ({ endpoint = '', data = '', config = {} }) =>
  axios.put(`${Constants.BASE_URL}/${endpoint}`, data, config)

const del = ({ endpoint = '', config = {} }) =>
  axios.delete(`${Constants.BASE_URL}/${endpoint}`, config)

const auth = (token = '') => ({ headers: { Authorization: token } })

const httpClient = {
  post,
  get,
  put,
  delete: del,
  auth,
}

export default httpClient
