import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import { connect } from 'react-redux'

const PrivateRoute = ({ component: Component, token, ...rest }) => (
  <Route
    {...rest}
    render={props => (
      token ? <Component {...props} />
      : <Redirect
        to={{
          pathname: '/login',
          state: { from: props.location },
        }}
        />
    )}
  />
)

const mapStateToProps = (state) => {
  const { token } = state.userReducer
  return { token }
}

export default connect(mapStateToProps)(PrivateRoute)
