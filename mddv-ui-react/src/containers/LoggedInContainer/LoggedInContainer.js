import React, { Component } from 'react'

class EnsureLoggedInContainer extends React.Component {
  
  componentDidMount() {
    const { dispatch, currentURL } = this.props

    if (!isLoggedIn) {
      // set the current url/path for future redirection (we use a Redux action)
      // then redirect (we use a React Router method)
      dispatch(setRedirectUrl(currentURL))
      browserHistory.replace("/login")
    }
  }

  render() {
    if (isLoggedIn) {
      return this.props.children
    } else {
      return null
    }
  }


}