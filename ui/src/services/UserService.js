import httpClient from './HttpClient'

const login = (username = '', password = '') =>
  httpClient.post({ endpoint: 'users/login', data: { usernameOrEmail: username, password } })

const findById = (id = 0) =>
  httpClient.get({ endpoint: `users/${id}` })

const findByName = (name = '') =>
  httpClient.get({ endpoint: `users/?name=${name}` })

const findByUsername = (name = '') =>
  httpClient.get({ endpoint: `users/?username=${name}` })

const getAll = () =>
  httpClient.get({ endpoint: 'users' })

const UserService = {
  login,
  findById,
  findByName,
  findByUsername,
  getAll,
}

export default UserService
