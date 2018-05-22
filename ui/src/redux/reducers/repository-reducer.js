import repositoryActionTypes from '../action-types/repository-action-types'
import extractorActionTypes from '../action-types/extractor-action-types'

const initialRepository = {
  name: null,
  type: null,
  location: null,
  id: 0,
}

const intitialState = {
  repository: initialRepository,
  loading: false,
  error: null,
  loadedExtractables: false,
  loadedRepositories: false,
  loadedRepository: false,
  repositories: [],
  extractableTypes: [],
  extractableEngines: [],
}

const repositoryReducer = (state = intitialState, action) => {
  switch (action.type) {
    case repositoryActionTypes.C_REPO:
      return { ...state, loading: true }
    case repositoryActionTypes.C_REPO_SUCCESS:
      return { ...state, loading: false, repository: action.payload, error: null }
    case repositoryActionTypes.C_REPO_FAILURE:
      return { ...state, loading: false, repository: {}, error: action.payload }
    case repositoryActionTypes.R_ALL_REPO:
      return { ...state, loading: true, loadedRepositories: false }
    case repositoryActionTypes.R_ALL_REPO_SUCCESS:
      return { ...state, loading: false, loadedRepositories: true, repositories: action.payload, error: null }
    case repositoryActionTypes.R_ALL_REPO_FAILURE:
      return { ...state, loading: false, loadedRepositories: false, repositories: [], error: action.payload }
    case repositoryActionTypes.R_REPO:
      return { ...state, loading: true, loadedRepository: false }
    case repositoryActionTypes.R_REPO_SUCCESS:
      return { ...state, loading: false, loadedRepository: true, repository: action.payload, error: null }
    case repositoryActionTypes.R_REPO_FAILURE:
      return { ...state, loading: false, loadedRepository: false, repository: initialRepository, error: action.payload }
    case repositoryActionTypes.REPO_RESET:
      return { ...state, loading: false, repository: initialRepository, loadedRepository: false }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES:
      return { ...state, loadedExtractables: false, loading: true }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_SUCCESS:
      return {
        ...state,
        loadedExtractables: true,
        extractableTypes: action.payload,
        extractableEngines: action.payload.map(x => ({ key: x.type, text: x.type, value: x.type })),
        error: null }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_FAILURE:
      return { ...state, loadedExtractables: false, loading: false, extractableTypes: [], error: action.payload }
    default:
      return state
  }
}

export default repositoryReducer
