import React from 'react'
import { List, Icon, ListItem, ListItemIcon, ListItemText } from '@material-ui/core'
import Group from 'mdi-material-ui/Group'
import Server from 'mdi-material-ui/Server'
import Dumbbell from 'mdi-material-ui/Dumbbell'
import { Link } from 'react-router-dom'
import './Sidenav.scss'

const Sidenav = () => (
  <List>
    <ListItem button component={Link} to="/repository">
      <ListItemIcon>
        <Server />
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
    <ListItem button component={Link} to="/technical">
      <ListItemIcon>
        <Dumbbell />
      </ListItemIcon>
      <ListItemText>
        Objetos Técnicos
      </ListItemText>
    </ListItem>
    <ListItem button component={Link} to="/model">
      <ListItemIcon>
        <Group />
      </ListItemIcon>
      <ListItemText>
        Modelos
      </ListItemText>
    </ListItem>
  </List>
)

export default Sidenav
