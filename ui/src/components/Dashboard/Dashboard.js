import React, { Component } from 'react'
import { Route, Switch, withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Typography } from '@material-ui/core'
import { Navigation, Welcome, Repositories, NotFound, Models, BusinessObjects, TechnicalObjects } from '../index'
import { Img } from '../../utils'
import './Dashboard.scss'

class Dashboard extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
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
              <Route path="/technical" component={TechnicalObjects} />
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
