import httpClient from './HttpClient'

const login = (username = '', password = '') =>
  httpClient.post({ endpoint: 'users/login', data: { usernameOrEmail: username, password } })

const UserService = {
  login,
}

export default UserService
