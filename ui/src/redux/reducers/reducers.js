import { combineReducers } from 'redux'
import userReducer from './user-reducer'
import repositoryReducer from './repository-reducer'
import navigationReducer from './navigation-reducer'

const rootReducer = combineReducers({
  userReducer,
  repositoryReducer,
  navigationReducer,
})

export default rootReducer
