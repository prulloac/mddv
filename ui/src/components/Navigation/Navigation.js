import React, { Component } from 'react'
import classNames from 'classnames'
import { AppBar, Toolbar, withStyles, IconButton, Drawer, Typography, Icon, Button } from '@material-ui/core'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Sidenav from '../Sidenav/Sidenav'
import { userActions } from '../../redux/actions'
import { Img, Session } from '../../utils'
import styles from './Navigation-style'
import './Navigation.scss'

class Navigation extends Component {
  constructor(props) {
    super(props)
    this.state = {
      open: true,
    }
  }

  logout = () => {
    const { dispatch } = this.props
    dispatch(userActions.logout())
  }

  handleDrawerOpen = () => {
    this.setState({ open: true })
  }

  handleDrawerClose = () => {
    this.setState({ open: false })
  }

  render() {
    const { classes } = this.props

    return (
      <div className={classes.root}>
        <AppBar
          position="absolute"
          className={classNames(classes.appBar, this.state.open && classes.appBarShift)}
        >
          <Toolbar disableGutters={!this.state.open}>
            <IconButton
              aria-label="open sidebar"
              onClick={this.handleDrawerOpen}
              className={classNames(classes.menuButton, this.state.open && classes.hide)}
            >
              <img alt="Mddv" src={Img.mddvLogo} width={45} />
            </IconButton>
            <Button variant="flat" color="inherit" href="/" size="small">
              Mddv
            </Button>
            <div className={classes.flex} />
            <Button
              color="inherit"
              onClick={this.logout}
              variant="flat"
              size="small"
            >
              <Icon>exit_to_app</Icon>
              <Typography variant="body2" autoCapitalize="ok" color="inherit" style={{ marginLeft: '0.5em' }}>
                Salir
              </Typography>
            </Button>
          </Toolbar>
        </AppBar>
        <Drawer
          variant="permanent"
          classes={{
            paper: classNames(classes.drawerPaper, !this.state.open && classes.drawerPaperClose),
          }}
          open={this.state.open}
        >
          <div style={{ position: 'relative', marginTop: '0px', width: '100%', backgroundImage: `url(${Img.officeBackground})`, height: '176px' }}>
            <div className={classes.toolbar} style={{ float: 'right' }}>
              <IconButton onClick={this.handleDrawerClose}>
                <img alt="Mddv" src={Img.mddvLogo} width={45} />
              </IconButton>
            </div>
            <div style={{ position: 'relative', padding: '32px 32px 0px' }}>
              <img alt={Session.getUserData.username} src={Img.maleAvatar} width={64} style={{ marginLeft: '25%' }} />
              <Typography
                variant="body2"
                autoCapitalize="ok"
                style={{ color: '#FFF' }}
                align="right"
              >
                {Session.getUserData().firstName} {Session.getUserData().lastName}
              </Typography>
              <Typography
                variant="body2"
                autoCapitalize="ok"
                style={{ color: '#FFF' }}
                align="right"
              >
                {Session.getUserData().email}
              </Typography>
              <Typography
                variant="body2"
                autoCapitalize="ok"
                style={{ color: '#FFF' }}
                align="right"
              >
                {Session.getUserData().username}
              </Typography>
            </div>
          </div>
          <Sidenav />
        </Drawer>
        <main className={classes.content}>
          <div className={classes.toolbar} />
          {this.props.children}
        </main>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading } = state.userReducer
  return { loading }
}

export default withStyles(styles, { withTheme: true })(withRouter(connect(mapStateToProps)(Navigation)))
