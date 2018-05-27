import notificationActionTypes from '../action-types/notification-action-types'

const initialState = {
  message: '',
  opentoast: false,
}

const notificationReducer = (state = initialState, action) => {
  switch (action.type) {
    case notificationActionTypes.POPUP_DIALOG:
      return { message: action.payload, opentoast: true }
    case notificationActionTypes.RESET_DIALOG:
      return { message: '', opentoast: false }
    default:
      return state
  }
}

export default notificationReducer
