import React from 'react'
import { Link } from 'react-router-dom'
import { SideNav, Icon, SideNavItem } from 'react-materialize'
import img from '../../utils/Img/Img'
import './Sidenav.scss'

const Sidenav = ({ trigger }) => (
  <SideNav
    trigger={trigger}
    options={{ closeOnClick: true }}
    className="mddv-sidenav"
  >
    <SideNavItem
      userView
      user={{
        image: img.maleAvatar,
        name: 'Admin',
        email: 'admin@mddv.com',
        background: img.officeBackground,
      }}
    />
    <li>
      <Link to="/repository">
        <Icon small left>storage</Icon>
        Repositorios
      </Link>
    </li>
    <SideNavItem divider />
    <li>
      <Link to="/businessObject">
        <Icon>card_travel</Icon>
        Objetos de Negocio
      </Link>
    </li>
    <SideNavItem divider />
    <li>
      <Link to="/model">
        <Icon>scatter_plot</Icon>
        Modelos
      </Link>
    </li>
  </SideNav>
)

export default Sidenav
