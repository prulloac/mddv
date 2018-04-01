import React, { Component } from 'react'
import { Redirect } from 'react-router'
import { Menu, Container, Image, Header, Segment, List, Divider, Icon, Sidebar } from 'semantic-ui-react'
import img from 'utils/Img'
import FakeAuth from 'utils/FakeAuth/FakeAuth'

import './Dashboard.scss'

class Dashboard extends Component {
  state = {
    sidebarVisible: false,
    logoutRedirect: false,
  }

  toggleSidebarVisibility = () => this.setState({ sidebarVisible: !this.state.sidebarVisible })

  logout = () => (
    FakeAuth.signout(() => this.setState({ logoutRedirect: true }))
  )

  render() {
    const { sidebarVisible, logoutRedirect } = this.state
    if (logoutRedirect) {
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

export default Dashboard
