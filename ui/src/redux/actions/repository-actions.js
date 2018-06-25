import RepositoryService from '../../services/RepositoryService'
import repositoryActionTypes from '../action-types/repository-action-types'
import extractorActionTypes from '../action-types/extractor-action-types'
import notificationActions from './notification-actions'

const resetRepository = () => {
  const reset = () => ({ type: repositoryActionTypes.REPO_RESET, payload: null })
  return dispatch => dispatch(reset())
}

const findById = (id = 0) => {
  const request = (repository) => ({ type: repositoryActionTypes.R_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    RepositoryService.findById(id).then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error cargando el repositorio solicitado'))
      },
    )
  }
}

const findByName = (name = '') => {
  const request = (repository) => ({ type: repositoryActionTypes.R_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.findByName(name).then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
    )
  }
}

const getAll = () => {
  const request = (repository) => ({ type: repositoryActionTypes.R_ALL_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_ALL_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_ALL_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    RepositoryService.getAll().then(
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No existen repositorios registrados'))
      },
    )
  }
}

const getExtractorCompatibles = () => {
  const request = () => ({ type: extractorActionTypes.R_ALL_EXTRACTABLE_TYPES, payload: 'fetching extractor compatibles' })
  const success = (compatibles) =>
    ({ type: extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_SUCCESS, payload: compatibles })
  const failure = (error) => ({ type: extractorActionTypes.R_ALL_EXTRACTABLE_TYPES_FAILURE, payload: error })
  return dispatch => {
    dispatch(request())
    RepositoryService.getExtractorCompatibles().then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
    )
  }
}

const create = ({ location = '', name = '', outsourced = false, engine = '', version = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.C_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.C_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.C_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, engine, version, location, outsourced }))
    RepositoryService.create({ location, name, outsourced, engine, version }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Repositorio exitosamente registrado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la creacion del repositorio'))
      },
    )
    dispatch(getAll())
  }
}

const update = ({ id = 0, location = '', name = '', version = '', outsourced = false, engine = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.U_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.U_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.U_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, engine, version, location, outsourced }))
    RepositoryService.update({ id, location, name, outsourced, version, engine }).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Repositorio exitosamente actualizado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error en la actualización del repositorio'))
      },
    )
  }
}

const del = (id = 0) => {
  const request = (repository) => ({ type: repositoryActionTypes.D_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.D_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.D_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    RepositoryService.delete(id).then(
      response => {
        dispatch(success(response))
        dispatch(notificationActions.notify('Repositorio exitosamente eliminado!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('Ha ocurrido un error al eliminar el repositorio'))
      },
    )
  }
}

const getConnectionParams = (id = 0) => {
  const request = (repository) => ({ type: repositoryActionTypes.R_ALL_PARAM, payload: repository })
  const success = (params) => ({ type: repositoryActionTypes.R_ALL_PARAM_SUCCESS, payload: params })
  const failure = (error) => ({ type: repositoryActionTypes.R_ALL_PARAM_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    RepositoryService.getConnectionParams(id).then(
      response => {
        dispatch(success(response.data.data))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No hay extractores compatibles para este repositorio'))
      },
    )
  }
}

const updateConnectionParams = (id = 0, params = {}) => {
  const request = (repository) => ({ type: repositoryActionTypes.U_PARAM, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.U_PARAM_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.U_PARAM_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ id, params }))
    RepositoryService.updateConnectionParams(id, params).then(
      response => {
        dispatch(success(response.data.data))
        dispatch(notificationActions.notify('Parámetros de conexión exitosamente actualizados!'))
      },
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No se han podido actualizar los parámetros de conexión para este repositorio'))
      },
    )
  }
}

const testRepository = (id = 0) => dispatch => {
  RepositoryService.testRepositoryConnection(id).then(
    response => {
      if (response.data.data) {
        dispatch(notificationActions.notify('Conexión exitosa!'))
      } else {
        dispatch(notificationActions.notify('Error de conexión, por favor verificar parámetros de conexión'))
      }
    },
    error => {
      dispatch(notificationActions.notify('Error de conexión, por favor verificar parámetros de conexión'))
      return error
    },
  )
}

const extract = (id = 0) => dispatch => {
  RepositoryService.extractFromRepository(id).then(
    response => {
      if (response.data.data) {
        dispatch(notificationActions.notify('Metadatos exitosamente extraidos desde el repositorio!'))
      } else {
        dispatch(notificationActions.notify('Error al extraer metadatos, por favor verificar parámetros de conexión'))
      }
    },
    error => {
      dispatch(notificationActions.notify('Error al extraer metadatos, por favor verificar parámetros de conexión'))
      return error
    },
  )
}


const repositoryActions = {
  create,
  findById,
  findByName,
  getAll,
  update,
  delete: del,
  getExtractorCompatibles,
  resetRepository,
  getConnectionParams,
  updateConnectionParams,
  testRepository,
  extract,
}

export default repositoryActions
