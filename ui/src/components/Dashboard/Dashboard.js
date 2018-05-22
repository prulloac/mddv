import React, { Component } from 'react'
import { Route, Switch, withRouter } from 'react-router-dom'
import { Footer } from 'react-materialize'
import { connect } from 'react-redux'
import img from 'utils/Img'
import Header from '../Header/Header'
import Welcome from '../Welcome/Welcome'
import Repositories from '../Repositories/Repositories'
import NotFound from '../NotFound/NotFound'
import repositoryActions from '../../redux/actions/repository-actions'
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
    const { user } = this.props
    return (
      <div>
        <Header user={user} />
        <div className="main-area">
          <Switch>
            <Route exact path="/" component={Welcome} />
            <Route path="/repository" component={Repositories} />
            <Route component={NotFound} />
          </Switch>
        </div>
        <Footer
          copyrights="Copyright &copy; 2018 Pablo Ricardo Ulloa Castro - MIT License"
          className="teal mddv-footer"
        >
          <img alt="Mddv" src={img.mddvLogo} className="footer-img" />
        </Footer>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { token, isAuthenticated, user } = state.userReducer
  return { token, isAuthenticated, user }
}

export default withRouter(connect(mapStateToProps)(Dashboard))
