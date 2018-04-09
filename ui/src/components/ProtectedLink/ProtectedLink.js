import React from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'
import './ProtectedLink.scss'

const ProtectedLink = ({
  roles = [''],
  to = '',
  children,
  user = { roles: [''] },
  ...rest
}) => (
  roles.filter(x => user.roles.indexOf(x) > -1).length > 0 ? (
    <Menu.Item as={Link} to={to} {...rest}>{children}</Menu.Item>
  ) : (
    null
  )
)

export default ProtectedLink
