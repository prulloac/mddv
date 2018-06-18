import React, { Component } from 'react'
import { withRouter, Switch, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography, Paper, Button } from '@material-ui/core'
import New from './NewBusinessObject'
import List from './ListBusinessObject'
import { title } from '../../utils'

import './BusinessObjects.scss'

class BusinessObjects extends Component {
  constructor(props) {
    super(props)
    title('Objetos de Negocio')
  }

  render() {
    const { match } = this.props
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Objetos de Negocio</Typography>
        </Paper>
        <div className="mddv-buttons" align="right">
          <Button variant="raised" component={Link} to={`${match.path}/new`}>
            Nuevo Objeto
          </Button>
        </div>
        <Switch>
          <Route path={`${match.path}/new`} component={New} />
          <Route component={List} />
        </Switch>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { repositories, repository } = state.businessObjectReducer
  return { repositories, repository }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(BusinessObjects)))
