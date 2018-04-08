import repositoryActionTypes from '../action-types/repository-action-types'

const intiialState = {
  repository: {},
  loading: false,
  error: '',
}

const repositoryReducer = (state = intiialState, action) => {
  switch (action.type) {
    case repositoryActionTypes.CREATE_REPOSITORY:
      return { ...state, loading: true }
    case repositoryActionTypes.CREATE_REPOSITORY_SUCCESS:
      return { ...state, loading: false, repository: action.payload }
    case repositoryActionTypes.CREATE_REPOSITORY_FAILURE:
      return {
        ...state,
        loading: false,
        repository: {},
        error: action.payload,
      }
    case repositoryActionTypes.LOAD_REPOSITORY:
      return { ...state, loading: true }
    case repositoryActionTypes.LOAD_REPOSITORY_SUCCESS:
      return { ...state, loading: false, repository: action.payload }
    case repositoryActionTypes.LOAD_REPOSITORY_FAILURE:
      return {
        ...state,
        loading: false,
        repository: {},
        error: action.payload,
      }
    default:
      return state
  }
}

export default repositoryReducer
