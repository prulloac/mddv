const FakeAuth = {
  isAuthenticated() {
    return localStorage.getItem('mddv-ukey')
  },
  username: '',
  authenticate(cb) {
    localStorage.setItem('mddv-ukey', 'true')
    setTimeout(cb, 100) // fake async
  },
  signout(cb) {
    localStorage.removeItem('mddv-ukey')
    this.username = ''
    setTimeout(cb, 100)
  },
  setUser(username) {
    this.username = username
  },
}

export default FakeAuth
