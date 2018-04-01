import React from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'

import './MenuLink.scss'

const MenuLink = ({ to, children, ...rest }) => (
  <Menu.Item {...rest} link><Link to={to}>{children}</Link></Menu.Item>
)

export default MenuLink
