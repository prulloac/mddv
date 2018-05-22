import React from 'react'
import { Navbar, NavItem } from 'react-materialize'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Sidenav from '../Sidenav/Sidenav'
import userActions from '../../redux/actions/user-actions'
import img from '../../utils/Img/Img'
import './Header.scss'

const userTemplate = {
  firstName: '',
  lastName: '',
}

const Header = ({ user } = { user: userTemplate }) => {
  const logout = () => {
    const { dispatch } = this.props
    dispatch(userActions.logout())
  }
  return (
    <Navbar className="teal lighten-2">
      <ul className="left"><Sidenav trigger={<img alt="Mddv" src={img.mddvLogo} className="logo-img" />} /></ul>
      <ul className="right hide-on-med-and-down">
        <NavItem>{user.firstName} {user.lastName}</NavItem>
        <NavItem onClick={logout}>Cerrar Sesión</NavItem>
      </ul>
    </Navbar>
  )
}

const mapStateToProps = state => {
  const { loading } = state.userReducer
  return { loading }
}

export default withRouter(connect(mapStateToProps)(Header))
