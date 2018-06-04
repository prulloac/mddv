import React from 'react'
import { List, Icon, ListItem, ListItemIcon, ListItemText } from '@material-ui/core'
import { Link } from 'react-router-dom'
import './Sidenav.scss'

const Sidenav = () => (
  <List>
    <ListItem button component={Link} to="/repository">
      <ListItemIcon>
        <Icon>storage</Icon>
      </ListItemIcon>
      <ListItemText>
        Repositorios
      </ListItemText>
    </ListItem>
    <ListItem button component={Link} to="/business">
      <ListItemIcon>
        <Icon>card_travel</Icon>
      </ListItemIcon>
      <ListItemText>
        Objetos de Negocio
      </ListItemText>
    </ListItem>
    <ListItem button component={Link} to="/model">
      <ListItemIcon>
        <Icon>scatter_plot</Icon>
      </ListItemIcon>
      <ListItemText>
        Modelos
      </ListItemText>
    </ListItem>
  </List>
)

export default Sidenav