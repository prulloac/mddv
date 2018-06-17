import ObjectService from '../../services/BusinessObjectService'
import objectActionTypes from '../action-types/object-action-types'
import notificationActions from './notification-actions'

const resetObject = () => ({ type: objectActionTypes.B_OBJECT_RESET, payload: {} })

const findById = (id = 0) => {
  const request = (repository) => ({ type: objectActionTypes.R_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    ObjectService.findById(id).then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error cargando el objeto solicitado'))
      },
    )
  }
}

const findByName = (name = '') => {
  const request = (repository) => ({ type: objectActionTypes.R_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    ObjectService.findByName(name).then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
    )
  }
}

const getAll = () => {
  const request = (repository) => ({ type: objectActionTypes.R_ALL_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_ALL_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_ALL_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    ObjectService.getAll().then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No existen objetos de negocio registrados'))
      },
    )
  }
}

const create = ({ name = '', description = '', type = '', version = '' }) => {
  const request = (repository) => ({ type: objectActionTypes.C_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.C_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.C_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, description }))
    ObjectService.create({ name, description, type, version }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente registrado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la creacion del objeto de negocio'))
      },
    )
    dispatch(getAll())
  }
}

const update = ({ id = 0, description = '', name = '', version = '', type = '' }) => {
  const request = (repository) => ({ type: objectActionTypes.U_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.U_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.U_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, description }))
    ObjectService.update({ id, description, name, version, type }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente actualizado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la actualización del objeto de negocio'))
      },
    )
  }
}

const del = (id = 0) => {
  const request = (repository) => ({ type: objectActionTypes.D_B_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.D_B_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.D_B_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    ObjectService.delete(id).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente eliminado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error al eliminar el objeto de negocio'))
      },
    )
  }
}


export default {
  resetObject,
  findById,
  findByName,
  delete: del,
  update,
  create,
  getAll,
}
