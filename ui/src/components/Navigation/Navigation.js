import React, { Component } from 'react'
import classNames from 'classnames'
import { AppBar, Toolbar, withStyles, IconButton, Drawer, Typography, Icon, Button } from '@material-ui/core'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
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
      open: false,
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
            <Icon>account_circle</Icon>
            <Typography
              variant="body2"
              autoCapitalize="ok"
              color="inherit"
              style={{ marginLeft: '1em', marginRight: '2em' }}
            >
              {Session.getUserData().username}
            </Typography>
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
          <div className={classes.toolbar}>
            <IconButton onClick={this.handleDrawerClose}>
              <ChevronLeftIcon />
            </IconButton>
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
