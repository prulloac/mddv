import ObjectService from '../../services/TechnicalObjectService'
import objectActionTypes from '../action-types/object-action-types'
import notificationActions from './notification-actions'


const resetObject = () => ({ type: objectActionTypes.T_OBJECT_RESET, payload: {} })

const findById = (id = 0) => {
  const request = (repository) => ({ type: objectActionTypes.R_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_T_OBJECT_FAILURE, payload: error })
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
  const request = (repository) => ({ type: objectActionTypes.R_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_T_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    ObjectService.findByName(name).then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
    )
  }
}

const getAll = () => {
  const request = (repository) => ({ type: objectActionTypes.R_ALL_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_ALL_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_ALL_T_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    ObjectService.getAll().then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No existen objetos técnicos registrados'))
      },
    )
  }
}

const getTypes = () => {
  const request = (repository) => ({ type: objectActionTypes.R_T_OBJECT_TYPES, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.R_T_OBJECT_TYPES_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.R_T_OBJECT_TYPES_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    ObjectService.getTypes().then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No hay tipos de Objetos Técnicos definidos. Contáctese con su administrador'))
      },
    )
  }
}

const create = ({ name = '', description = '', type = '', version = '' }) => {
  const request = (repository) => ({ type: objectActionTypes.C_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.C_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.C_T_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, description }))
    ObjectService.create({ name, description, type, version }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente registrado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la creacion del objeto técnico'))
      },
    )
    dispatch(getAll())
  }
}

const update = ({ id = 0, description = '', name = '', version = '', type = '' }) => {
  const request = (repository) => ({ type: objectActionTypes.U_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.U_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.U_T_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, description }))
    ObjectService.update({ id, description, name, version, type }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente actualizado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la actualización del objeto técnico'))
      },
    )
  }
}

const del = (id = 0) => {
  const request = (repository) => ({ type: objectActionTypes.D_T_OBJECT, payload: repository })
  const success = (repository) => ({ type: objectActionTypes.D_T_OBJECT_SUCCESS, payload: repository })
  const failure = (error) => ({ type: objectActionTypes.D_T_OBJECT_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    ObjectService.delete(id).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Objeto exitosamente eliminado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error al eliminar el objeto técnico'))
      },
    )
  }
}

const getRepositories = () => {
  const request = () => ({ type: objectActionTypes.R_ALL_T_OBJECT_REPO, payload: null })
  const success = (repositories) => ({ type: objectActionTypes.R_ALL_T_OBJECT_REPO_SUCCESS, payload: repositories })
  const failure = (error) => ({ type: objectActionTypes.R_ALL_T_OBJECT_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    ObjectService.getRepositories().then(
      response => {
        dispatch(success(response.data.data))
        dispatch(notificationActions.notify('Se han encontrado objetos de tipo repositorio!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error al obtener objetos'))
      },
    )
  }
}

const getChildren = (id = 0) => {
  const request = (parent) => ({ type: objectActionTypes.R_ALL_T_OBJECT_CHILDREN, payload: parent })
  const success = (children) => ({ type: objectActionTypes.R_ALL_T_OBJECT_CHILDREN_SUCCESS, payload: children })
  const failure = (error) => ({ type: objectActionTypes.R_ALL_T_OBJECT_CHILDREN_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    ObjectService.getChildren(id).then(
      response => {
        dispatch(success(response.data.data))
        dispatch(notificationActions.notify('Se han encontrado objetos técnicos!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error al obtener objetos'))
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
  getTypes,
  getRepositories,
  getChildren,
}
