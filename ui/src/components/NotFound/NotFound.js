import React, { Component } from 'react'
import { Typography, Paper } from '@material-ui/core'
import { title } from '../../utils'
import './NotFound.scss'

class NotFound extends Component {
  constructor(props) {
    super(props)
    title()
  }

  render() {
    return (
      <Paper elevation={4}>
        <Typography variant="display1" align="center">Página no encontrada</Typography>
      </Paper>
    )
  }
}

export default NotFound
