import { combineReducers } from 'redux'
import userReducer from './user-reducer'
import repositoryReducer from './repository-reducer'
import notificationReducer from './notification-reducer'

const rootReducer = combineReducers({
  userReducer,
  repositoryReducer,
  notificationReducer,
})

export default rootReducer
export { rootReducer }
