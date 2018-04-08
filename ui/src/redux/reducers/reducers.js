import { combineReducers } from 'redux'
import userReducer from './user-reducer'
import repositoryReducer from './repository-reducer'

const rootReducer = combineReducers({
  userReducer,
  repositoryReducer,
})

export default rootReducer
