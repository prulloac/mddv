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

const create = ({ location = '', name = '', outsourced = false, type = '', version = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.C_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.C_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.C_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, location, outsourced }))
    RepositoryService.create({ location, name, outsourced, type, version }).then(
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

const update = ({ id = 0, location = '', name = '', version = '', outsourced = false, type = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.U_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.U_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.U_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request({ name, type, version, location, outsourced }))
    RepositoryService.update({ id, location, name, outsourced, version, type }).then(
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
      response => dispatch(success(response.data.data)),
      error => {
        dispatch(failure(error))
        dispatch(notificationActions.notify('No hay extractores compatibles para este repositorio'))
      },
    )
  }
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
}

export default repositoryActions
