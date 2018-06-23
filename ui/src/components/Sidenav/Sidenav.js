import React from 'react'
import { List, Icon, ListItem, ListItemIcon, ListItemText } from '@material-ui/core'
import Database from 'mdi-material-ui/Database'
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
    <ListItem button component={Link} to="/technical">
      <ListItemIcon>
        <Database />
      </ListItemIcon>
      <ListItemText>
        Objetos Técnicos
      </ListItemText>
    </ListItem>
    <ListItem button component={Link} to="/model">
      <ListItemIcon>
        <Icon>bubble_chart</Icon>
      </ListItemIcon>
      <ListItemText>
        Modelos
      </ListItemText>
    </ListItem>
  </List>
)

export default Sidenav
