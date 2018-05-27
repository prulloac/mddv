import notificationActionTypes from '../action-types/notification-action-types'

const notify = (message = '') => ({ type: notificationActionTypes.POPUP_DIALOG, payload: message })
const resetNotification = () => ({ type: notificationActionTypes.RESET_DIALOG, payload: null })

const notificationActions = {
  notify,
  resetNotification,
}

export default notificationActions
