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
    default:
      return { ...state }
  }
}

export default technicalObjectReducer
