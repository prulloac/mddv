import userActionTypes from '../action-types/user-action-types'

const initialState = {
  user: {
  },
  token: '',
  loading: false,
  error: '',
  isAuthenticated: false,
}

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case userActionTypes.FETCH_TOKEN:
      return { ...state, loading: true }
    case userActionTypes.FETCH_TOKEN_SUCCESS:
      return {
        ...state,
        loading: false,
        token: action.payload.token,
        user: action.payload.user,
        isAuthenticated: true,
      }
    case userActionTypes.FETCH_TOKEN_FAILURE:
      return {
        ...state,
        loading: false,
        token: '',
        isAuthenticated: false,
        error: action.payload,
      }
    case userActionTypes.TOKEN_RESET:
      return initialState
    default:
      return state
  }
}

export default userReducer
