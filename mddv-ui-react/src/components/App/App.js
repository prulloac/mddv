import React, { Component } from 'react'
import NavBarContainer from 'containers/NavBar'
import SideBarContainer from 'containers/SideBar'
import MainPanelContainer from 'containers/MainPanel'

import './App.scss'

class App extends Component {
  render() {
    return (
      <div>
        <NavBarContainer />
        <SideBarContainer />
        <MainPanelContainer />
      </div>
    )
  }
}

export default App
