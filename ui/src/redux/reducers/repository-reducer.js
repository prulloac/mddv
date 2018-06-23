import repositoryActionTypes from '../action-types/repository-action-types'
import extractorActionTypes from '../action-types/extractor-action-types'

const initialRepository = {
  name: null,
  engine: null,
  location: null,
  id: 0,
  connectionParams: [
    { name: '', value: '' },
  ],
}

const intitialState = {
  repository: initialRepository,
  loading: false,
  error: null,
  loadedExtractables: false,
  loadedRepositories: false,
  loadedRepository: false,
  loadedConnectionParams: false,
  repositories: [],
  extractableEngines: [],
  connectionParams: [],
}

const repositoryReducer = (state = intitialState, action) => {
  switch (action.type) {
    case repositoryActionTypes.C_REPO:
      return { ...state, loading: true, error: null }
    case repositoryActionTypes.C_REPO_SUCCESS:
      return { ...state, loading: false, repository: action.payload }
    case repositoryActionTypes.C_REPO_FAILURE:
      return { ...state, loading: false, repository: {}, error: action.payload }
    case repositoryActionTypes.R_ALL_REPO:
      return { ...state, loading: true, loadedRepositories: false, error: null }
    case repositoryActionTypes.R_ALL_REPO_SUCCESS:
      return { ...state, loading: false, loadedRepositories: true, repositories: action.payload }
    case repositoryActionTypes.R_ALL_REPO_FAILURE:
      return { ...state, loading: false, loadedRepositories: false, repositories: [], error: action.payload }
    case repositoryActionTypes.R_REPO:
      return { ...state, loading: true, loadedRepository: false, error: null }
    case repositoryActionTypes.R_REPO_SUCCESS:
      return { ...state, loading: false, loadedRepository: true, repository: action.payload }
    case repositoryActionTypes.R_REPO_FAILURE:
      return { ...state, loading: false, loadedRepository: false, repository: initialRepository, error: action.payload }
    case repositoryActionTypes.REPO_RESET:
      return { ...state, loading: false, repository: initialRepository, loadedRepository: false, error: null }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES:
      return { ...state, loadedExtractables: false, loading: true, error: null }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_SUCCESS:
      return {
        ...state,
        loadedExtractables: true,
        extractableEngines: action.payload.map(x => ({ key: x.type, text: x.type, value: x.type })),
      }
    case extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_FAILURE:
      return { ...state, loadedExtractables: false, loading: false, error: action.payload }
    case repositoryActionTypes.R_ALL_PARAM:
      return { ...state, loadedConnectionParams: false, loading: true, connectionParams: [], error: null }
    case repositoryActionTypes.R_ALL_PARAM_FAILURE:
      return { ...state, loadedConnectionParams: false, loading: false, connectionParams: [], error: action.payload }
    case repositoryActionTypes.R_ALL_PARAM_SUCCESS:
      return { ...state, loadedConnectionParams: true, loading: false, connectionParams: action.payload, error: null }
    default:
      return state
  }
}

export default repositoryReducer
