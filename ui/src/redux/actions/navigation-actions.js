import navigationActionTypes from '../action-types/navigation-action-types'

const goTo = (location = '') => ({ type: navigationActionTypes.CHANGE_CURRENT, payload: location })

const navigationActions = {
  goTo,
}

export default navigationActions
