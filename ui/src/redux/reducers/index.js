import { combineReducers } from 'redux'
import userReducer from './user-reducer'
import repositoryReducer from './repository-reducer'
import notificationReducer from './notification-reducer'
import businessObjectReducer from './business-objects-reducer'
import technicalObjectReducer from './technical-objects-reducer'

const rootReducer = combineReducers({
  userReducer,
  repositoryReducer,
  notificationReducer,
  businessObjectReducer,
  technicalObjectReducer,
})

export { rootReducer }
export { userReducer }
export { repositoryReducer }
export { notificationReducer }
export { businessObjectReducer }
export { technicalObjectReducer }
