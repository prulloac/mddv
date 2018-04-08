import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'

import store from 'redux/store'
import App from 'components/App'

import 'semantic-ui-css/semantic.min.css'
import './style.css'

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.querySelector('#main'),
)

if (module.hot) {
  module.hot.accept()
}
