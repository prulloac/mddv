import React, { Component } from 'react'
import { Switch, Route } from 'react-router-dom'

import MainPanel from 'components/MainPanel'
import LoggedInContainer from 'containers/LoggedInContainer'

class MainPanelContainer extends Component {
  constructor(props){
    super(props)
    this.state = {

    }
  }

  render(){
    return (
      <main>
        <Switch>
          <Route exact path='/login' component={Login} />
          <Route component={LoggedInContainer}>
            <Route exact path='/' component={Home} />
            <Route exact path='/logout' component={Logout} />
          </Route>
        </Switch>
      </main>
    )
  }
}

export default MainPanelContainer
