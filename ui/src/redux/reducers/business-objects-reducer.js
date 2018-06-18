import objectActionTypes from '../action-types/object-action-types'

const initialState = {
  businessObject: {},
  error: '',
  loading: false,
  businessObjects: [],
  loadedBusinessObjects: false,
  businessObjectTypes: [],
  loadedBusinessObjectTypes: false,
}

const businessObjectReducer = (state = initialState, action = { type: '', payload: {} }) => {
  switch (action.type) {
    case objectActionTypes.B_OBJECT_RESET:
      return { ...state, businessObject: {}, error: null }
    case objectActionTypes.C_B_OBJECT:
      return { ...state, loading: true, error: null }
    case objectActionTypes.C_B_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, businessObject: action.payload }
    case objectActionTypes.C_B_OBJECT_FAILURE:
      return { ...state, loading: false, error: action.payload, businessObject: {} }
    case objectActionTypes.R_B_OBJECT_TYPES:
      return { ...state, loading: true, error: null }
    case objectActionTypes.R_B_OBJECT_TYPES_SUCCESS:
      return { ...state, loadedBusinessObjectTypes: true, businessObjectTypes: action.payload }
    case objectActionTypes.R_B_OBJECT_TYPES_FAILURE:
      return { ...state, loadedBusinessObjectTypes: false, businessObjectTypes: [], error: action.payload }
    case objectActionTypes.R_ALL_B_OBJECT:
      return { ...state, loading: true, error: null, loadedBusinessObjects: false, businessObjects: [] }
    case objectActionTypes.R_ALL_B_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, loadedBusinessObjects: true, businessObjects: action.payload }
    case objectActionTypes.R_ALL_B_OBJECT_FAILURE:
      return { ...state, loading: false, error: action.payload, loadedBusinessObjects: false, businessObjects: [] }
    default:
      return { ...state }
  }
}

export default businessObjectReducer
