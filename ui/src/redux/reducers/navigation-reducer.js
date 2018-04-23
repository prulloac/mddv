import navigationActionTypes from '../action-types/navigation-action-types'

const intiialState = {
  currentPage: '/',
  navigationHistory: [],
}

const navigationReducer = (state = intiialState, action) => {
  switch (action.type) {
    case navigationActionTypes.CHANGE_CURRENT:
      state.navigationHistory.push(action.payload)
      return { navigationHistory: state.navigationHistory, currentPage: action.payload }
    default:
      return state
  }
}

export default navigationReducer
