import React, { Component } from 'react'
import PropTypes from 'prop-types'

import './App.scss'

class App extends Component {
  render() {
    const { prop } = this.props
    return (
      <div>
        {prop}
      </div>
    )
  }
}

App.propTypes = {
  prop: PropTypes.string,
}

App.defaultProps = {
  prop: '',
}

export default App
