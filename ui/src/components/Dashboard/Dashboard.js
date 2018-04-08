import React, { Component } from 'react'
import { Redirect } from 'react-router'
import { Menu, Container, Image, Header, Segment, List, Divider, Icon, Sidebar } from 'semantic-ui-react'
import { connect } from 'react-redux'
import img from 'utils/Img'
import userActions from '../../redux/actions/user.actions'

import './Dashboard.scss'

class ConnectedDashboard extends Component {
  constructor(props) {
    super(props)
    this.state = {
      sidebarVisible: false,
    }
    this.logout = this.logout.bind(this)
    this.toggleSidebarVisibility = this.toggleSidebarVisibility.bind(this)
  }

  toggleSidebarVisibility = () => this.setState({ sidebarVisible: !this.state.sidebarVisible })

  logout = () => {
    const { dispatch } = this.props
    dispatch(userActions.logout())
  }

  render() {
    const { sidebarVisible } = this.state
    const { isAuthenticated } = this.props
    if (!isAuthenticated) {
      return (
        <Redirect to="/login" />
      )
    }

    return (
      <div>
        <Menu fixed="top" inverted>
          <Menu.Item as="a" icon="ellipsis horizontal" onClick={this.toggleSidebarVisibility} />
          <Menu.Item as="a" header>
            <Image
              size="mini"
              src={img.mddvLogo}
              style={{ marginRight: '1.5em' }}
            />
            MDDV
          </Menu.Item>
          <Menu.Item as="a">Inicio</Menu.Item>
          <Menu.Item as="a" onClick={this.logout} position="right">Cerrar Sesión</Menu.Item>
        </Menu>
        <Sidebar.Pushable as={Segment} style={{ border: 0, marginTop: '4em' }}>
          <Sidebar
            as={Menu}
            animation="push"
            width="thin"
            visible={sidebarVisible}
            icon="labeled"
            fixed="left"
            vertical
            inverted
            >
            <Menu.Item name="home">
              <Icon name="home" />
              Home
            </Menu.Item>
            <Menu.Item name="gamepad">
              <Icon name="gamepad" />
              Games
            </Menu.Item>
            <Menu.Item name="camera">
              <Icon name="camera" />
              Channels
            </Menu.Item>
          </Sidebar>
          <Sidebar.Pusher>
            <Segment basic>
              <Container text style={{ marginTop: '7em' }}>
                <Header as="h1">Semantic UI React Fixed Template</Header>
                <p>This is a basic fixed menu template using fixed size containers.</p>
                <p>
A text container is used for the main container,
which is useful for single column layouts.
                </p>
                <Image src={img.mediaParagraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
                <Image src={img.paragraph} style={{ marginTop: '2em' }} />
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
                <List horizontal inverted divided link>
                  <List.Item as="a" href="#">Site Map</List.Item>
                  <List.Item as="a" href="#">Contact Us</List.Item>
                  <List.Item as="a" href="#">Terms and Conditions</List.Item>
                  <List.Item as="a" href="#">Privacy Policy</List.Item>
                </List>
              </Container>
            </Segment>
          </Sidebar.Pusher>
        </Sidebar.Pushable>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { token, isAuthenticated } = state.userReducer
  return { token, isAuthenticated }
}

const Dashboard = connect(mapStateToProps)(ConnectedDashboard)

export default Dashboard
