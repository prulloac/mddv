import React, { Component } from 'react'
import classNames from 'classnames'
import { AppBar, Toolbar, withStyles, IconButton, Drawer, Typography } from '@material-ui/core'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Sidenav from '../Sidenav/Sidenav'
import userActions from '../../redux/actions/user-actions'
import img from '../../utils/Img/Img'
import styles from './Navigation-style'

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
              color="inherit"
              aria-label="open drawer"
              onClick={this.handleDrawerOpen}
              className={classNames(classes.menuButton, this.state.open && classes.hide)}
            >
              <img alt="Mddv" src={img.mddvLogo} width={45} />
            </IconButton>
            <Typography variant="title" color="inherit" noWrap>
              Mddv
            </Typography>
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
