const FakeAuth = {
  isAuthenticated: false,
  username: '',
  authenticate(cb, username) {
    this.isAuthenticated = true
    this.username = username
    setTimeout(cb, 100) // fake async
  },
  signout(cb) {
    this.isAuthenticated = false
    this.username = ''
    setTimeout(cb, 100)
  },
}

export default FakeAuth
