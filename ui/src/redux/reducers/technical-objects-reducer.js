import objectActionTypes from '../action-types/object-action-types'

const initialState = {
  error: '',
  loading: false,
  technicalObject: {},
  loadedTechnicalObject: false,
  technicalObjects: [],
  loadedTechnicalObjects: false,
  technicalObjectTypes: [],
  loadedTechnicalObjectTypes: false,
  graphdata: {},
  loadedGraphdata: false,
}

const technicalObjectReducer = (state = initialState, action = { type: '', payload: {} }) => {
  switch (action.type) {
    case objectActionTypes.T_OBJECT_RESET:
      return { ...state, technicalObject: {}, error: null }
    case objectActionTypes.C_T_OBJECT:
      return { ...state, loading: true, error: null }
    case objectActionTypes.C_T_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, technicalObject: action.payload }
    case objectActionTypes.C_T_OBJECT_FAILURE:
      return { ...state, loading: false, error: action.payload, technicalObject: {} }
    case objectActionTypes.T_OBJECT_TYPES_RESET:
      return { ...state, loading: false, error: null, technicalObjectTypes: [], loadedTechnicalObjectTypes: false }
    case objectActionTypes.R_T_OBJECT_TYPES:
      return { ...state, loading: true, error: null }
    case objectActionTypes.R_T_OBJECT_TYPES_SUCCESS:
      return { ...state, loadedTechnicalObjectTypes: true, technicalObjectTypes: action.payload }
    case objectActionTypes.R_T_OBJECT_TYPES_FAILURE:
      return { ...state, loadedTechnicalObjectTypes: false, technicalObjectTypes: [], error: action.payload }
    case objectActionTypes.R_ALL_T_OBJECT:
      return { ...state, loading: true, error: null, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_ALL_T_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, loadedTechnicalObjects: true, technicalObjects: action.payload }
    case objectActionTypes.R_ALL_T_OBJECT_FAILURE:
      return { ...state, loading: false, error: action.payload, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_T_OBJECT:
      return { ...state, loading: true, error: null, loadedTechnicalObject: false, technicalObject: {} }
    case objectActionTypes.R_T_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, loadedTechnicalObject: true, technicalObject: action.payload }
    case objectActionTypes.R_T_OBJECT_FAILURE:
      return { ...state, loading: false, error: action.payload, loadedTechnicalObject: false, technicalObject: {} }
    case objectActionTypes.R_ALL_T_OBJECT_REPO:
      return { ...state, loading: true, error: null, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_ALL_T_OBJECT_REPO_SUCCESS:
      return { ...state, loading: false, error: null, loadedTechnicalObjects: true, technicalObjects: action.payload }
    case objectActionTypes.R_ALL_T_OBJECT_REPO_FAILURE:
      return { ...state, loading: false, error: action.payload, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_ALL_T_OBJECT_CHILDREN:
      return { ...state, loading: true, error: null, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_ALL_T_OBJECT_CHILDREN_SUCCESS:
      return { ...state, loading: false, error: null, loadedTechnicalObjects: true, technicalObjects: action.payload }
    case objectActionTypes.R_ALL_T_OBJECT_CHILDREN_FAILURE:
      return { ...state, loading: false, error: action.payload, loadedTechnicalObjects: false, technicalObjects: [] }
    case objectActionTypes.R_T_OBJECT_GRAPH:
      return { ...state, loading: true, error: null, graphdata: {}, loadedGraphdata: false }
    case objectActionTypes.R_T_OBJECT_GRAPH_SUCCESS:
      return { ...state, loading: false, graphdata: action.payload, loadedGraphdata: true }
    case objectActionTypes.R_T_OBJECT_GRAPH_FAILURE:
      return { ...state, loading: false, error: action.payload, graphdata: {}, loadedGraphdata: false }
    default:
      return { ...state }
  }
}

export default technicalObjectReducer
