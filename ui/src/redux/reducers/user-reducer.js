import userActionTypes from '../action-types/user-action-types'

const initialState = {
  loading: false,
  error: false,
  isAuthenticated: false,
}

const userReducer = (state = initialState, action = { type: '', payload: {} }) => {
  switch (action.type) {
    case userActionTypes.FETCH_TOKEN:
      return { ...state, loading: true }
    case userActionTypes.FETCH_TOKEN_SUCCESS:
      return { ...state, loading: false, isAuthenticated: true, error: false }
    case userActionTypes.FETCH_TOKEN_FAILURE:
      return { ...state, loading: false, isAuthenticated: false, error: true }
    case userActionTypes.TOKEN_RESET:
      return initialState
    default:
      return state
  }
}

export default userReducer
