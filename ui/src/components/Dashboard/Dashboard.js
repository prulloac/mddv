import React, { Component } from 'react'
import { Menu, Container, Image, Segment, Divider, Icon, Sidebar } from 'semantic-ui-react'
import { connect } from 'react-redux'
import img from 'utils/Img'
import userActions from '../../redux/actions/user-actions'
import navigationActions from '../../redux/actions/navigation-actions'

import './Dashboard.scss'
import router from '../../utils/Router/Router'

class Dashboard extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.logout = this.logout.bind(this)
    this.handleRedirect = this.handleRedirect.bind(this)
    if (this.props.currentPage !== '/home') {
      this.props.dispatch(navigationActions.goTo('/home'))
    }
  }

  logout = () => {
    const { dispatch } = this.props
    dispatch(userActions.logout())
  }

  handleRedirect = (event, { to }) => {
    const { dispatch, currentPage } = this.props
    if (currentPage !== to) {
      dispatch(navigationActions.goTo(to))
    }
  }

  render() {
    const { user, currentPage } = this.props
    const currentComponent = router(currentPage)
    return (
      <div>
        <Menu fixed="top" pointing style={{ zIndex: 999 }}>
          <Menu.Item header to="/home" onClick={this.handleRedirect}>
            <Image
              size="mini"
              src={img.mddvLogo}
              style={{ marginRight: '3.5em' }}
            />
            MDDV
          </Menu.Item>
          <Menu.Item as="a">{user.firstName} {user.lastName}</Menu.Item>
          <Menu.Item as="a" onClick={this.logout} position="right">Cerrar Sesión</Menu.Item>
        </Menu>
        <Segment>
          <Sidebar
            as={Menu}
            animation="push"
            width="thin"
            visible
            icon="labeled"
            fixed="left"
            vertical
            inverted
            style={{ border: 0, top: '4.6em' }}
            >
            <Menu.Item name="repositorios" to="/repositorios" onClick={this.handleRedirect}>
              <Icon name="database" />
              Repositorios
            </Menu.Item>
            <Menu.Item name="objetosNegocio" to="/objetosNegocio" onClick={this.handleRedirect}>
              <Icon name="travel" />
              Objetos de Negocio
            </Menu.Item>
            <Menu.Item name="modelos" to="/modelos" onClick={this.handleRedirect}>
              <Icon name="cube" />
              Modelos
            </Menu.Item>
          </Sidebar>
        </Segment>
        <Segment basic>
          <Container text style={{ marginTop: '4em' }}>
            {currentComponent}
          </Container>
        </Segment>
        <Segment
          inverted
          vertical
          style={{ margin: '5em 0em 0em', padding: '1em 0em' }}
          >
          <Container textAlign="center">
            <Divider inverted section />
            <Image
              centered
              size="mini"
              src={img.mddvLogo}
            />
          </Container>
        </Segment>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { token, isAuthenticated, user } = state.userReducer
  const { navigationHistory, currentPage } = state.navigationReducer
  return { token, isAuthenticated, user, navigationHistory, currentPage }
}

export default connect(mapStateToProps)(Dashboard)
