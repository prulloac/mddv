import React from 'react'
import ReactDOM from 'react-dom'
import { HashRouter } from 'react-router-dom'

import App from 'components/App'

import 'semantic-ui-css/semantic.min.css'
import './style.css'

ReactDOM.render(
  <HashRouter>
    <App />
  </HashRouter>,
  document.querySelector('#main'),
)

if (module.hot) {
  module.hot.accept()
}
