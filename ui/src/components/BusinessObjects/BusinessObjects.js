import React, { Component } from 'react'
import { withRouter, Switch, Route } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography, Paper } from '@material-ui/core'
import New from './NewBusinessObject'
import List from './ListBusinessObject'
import Edit from './EditBusinessObject'
import Relate from './RelateBusinessObject'
import { title } from '../../utils/UtilityFunctions'
import businessObjectActions from '../../redux/actions/business-object-actions'
import './BusinessObjects.scss'

class BusinessObjects extends Component {
  constructor(props) {
    super(props)
    title('Objetos de Negocio')
    this.props.dispatch(businessObjectActions.getTypes())
  }

  render() {
    const { match } = this.props
    return (
      <div>
        <Paper elevation={4}>
          <Typography variant="display1" align="center">Objetos de Negocio</Typography>
        </Paper>
        <Switch>
          <Route path={`${match.path}/new`} component={New} />
          <Route path={`${match.path}/edit/:id`} component={Edit} />
          <Route path={`${match.path}/relations/:id`} component={Relate} />
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
