import React from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'
import FakeAuth from 'utils/FakeAuth'
import './ProtectedLink.scss'

const ProtectedLink = ({
  roles,
  to,
  children,
  ...rest
}) => (
  roles.findIndex((x) => x === FakeAuth.role) !== -1 ? (
    <Menu.Item {...rest} link><Link to={to}>{children}</Link></Menu.Item>
  ) : (
    null
  )
)

export default ProtectedLink
