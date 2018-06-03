import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Snackbar, withStyles } from '@material-ui/core'
import { notificationActions } from '../../redux/actions'

class Notification extends Component {
  handleClose = () => {
    this.props.dispatch(notificationActions.resetNotification())
  }

  render() {
    const { opentoast, message } = this.props
    return (
      <Snackbar
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        open={opentoast}
        autoHideDuration={3000}
        onClose={this.handleClose}
        ContentProps={{
          'aria-describedby': 'message-id',
        }}
        message={<span id="message-id">{message}</span>}
      />
    )
  }
}

const mapStateToProps = state => {
  const { opentoast, message } = state.notificationReducer
  return { opentoast, message }
}

export default withStyles(null)(connect(mapStateToProps)(Notification))
