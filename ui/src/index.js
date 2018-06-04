import React from 'react'
import ReactDOM from 'react-dom'
import { HashRouter } from 'react-router-dom'
import { Provider } from 'react-redux'

import { store } from 'redux/store'
import { App } from './components'

ReactDOM.render(
  <HashRouter>
    <Provider store={store}>
      <App />
    </Provider>
  </HashRouter>,
  document.querySelector('#main'),
)

if (module.hot) {
  module.hot.accept()
}
