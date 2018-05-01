import repositoryActionTypes from '../action-types/repository-action-types'

const intiialState = {
  repository: {},
  loading: false,
  error: null,
  repositories: [],
}

const repositoryReducer = (state = intiialState, action) => {
  switch (action.type) {
    case repositoryActionTypes.C_REPO:
      return { ...state, loading: true }
    case repositoryActionTypes.C_REPO_SUCCESS:
      return { ...state, loading: false, repository: action.payload, error: null }
    case repositoryActionTypes.C_REPO_FAILURE:
      return { ...state, loading: false, repository: {}, error: action.payload }
    case repositoryActionTypes.R_ALL_REPO:
      return { ...state, loading: true }
    case repositoryActionTypes.R_ALL_REPO_SUCCESS:
      return { ...state, loading: false, repositories: action.payload, error: null }
    case repositoryActionTypes.R_ALL_REPO_FAILURE:
      return { ...state, loading: false, repositories: [], error: action.payload }
    case repositoryActionTypes.R_REPO:
      return { ...state, loading: true }
    case repositoryActionTypes.R_REPO_SUCCESS:
      return { ...state, loading: false, repository: action.payload, error: null }
    case repositoryActionTypes.R_REPO_FAILURE:
      return { ...state, loading: false, repository: {}, error: action.payload }
    default:
      return state
  }
}

export default repositoryReducer
