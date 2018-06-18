import objectActionTypes from '../action-types/object-action-types'

const initialState = {
  businessObject: {},
  error: null,
  loading: false,
  businessObjects: [],
  loadedBusinessObjects: false,
  businessObjectTypes: [],
  loadedBusinessObjectTypes: false,
}

const businessObjectReducer = (state = initialState, action = { type: '', payload: {} }) => {
  switch (action.type) {
    case objectActionTypes.B_OBJECT_RESET:
      return { ...state, businessObject: action.payload, error: null }
    case objectActionTypes.C_B_OBJECT:
      return { ...state, loading: true, error: null }
    case objectActionTypes.C_B_OBJECT_SUCCESS:
      return { ...state, loading: false, error: null, businessObject: action.payload }
    default:
      return { ...state }
  }
}

export default businessObjectReducer
