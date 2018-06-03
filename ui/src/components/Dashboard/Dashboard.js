import React, { Component } from 'react'
import { Route, Switch, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography } from '@material-ui/core'
import Navigation from '../Navigation/Navigation'
import Welcome from '../Welcome/Welcome'
import Repositories from '../Repositories/Repositories'
import NotFound from '../NotFound/NotFound'
import Models from '../Models/Models'
import BusinessObjects from '../BusinessObjects/BusinessObjects'
import repositoryActions from '../../redux/actions/repository-actions'
import { Img } from '../../utils'
import './Dashboard.scss'

class Dashboard extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(repositoryActions.getExtractorCompatibles())
    this.props.dispatch(repositoryActions.getAll())
  }

  render() {
    return (
      <div>
        <Navigation>
          <div className="main-area">
            <Switch>
              <Route exact path="/" component={Welcome} />
              <Route path="/repository" component={Repositories} />
              <Route path="/model" component={Models} />
              <Route path="/business" component={BusinessObjects} />
              <Route component={NotFound} />
            </Switch>
          </div>
        </Navigation>
        <div className="teal mddv-footer">
          <img alt="Mddv" src={Img.mddvLogo} className="footer-img" />
          <Typography>
            Copyright &copy; 2018 Pablo Ricardo Ulloa Castro - MIT License
          </Typography>
        </div>
      </div>
    )
  }
}

export default withStyles(null)(withRouter(connect(null)(Dashboard)))
