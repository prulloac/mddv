import RepositoryService from '../../services/RepositoryService'
import repositoryActionTypes from '../action-types/repository-action-types'

const create = ({ location = '', name = '', outsourced = false, type = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.C_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.C_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.C_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.create({ location, name, outsourced, type }).then(
      response => dispatch(success(response)),
      error => dispatch(failure(error)),
    )
  }
}

const update = ({ id = 0, location = '', name = '', outsourced = false, type = '' }) => {
  const request = (repository) => ({ type: repositoryActionTypes.U_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.U_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.U_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.update({ id, location, name, outsourced, type }).then(
      response => dispatch(success(response)),
      error => dispatch(failure(error)),
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
      response => dispatch(success(response)),
      error => dispatch(failure(error)),
    )
  }
}

const findById = (id = 0) => {
  const request = (repository) => ({ type: repositoryActionTypes.R_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(id))
    RepositoryService.findById(id).then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
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

const getAll = (name = '') => {
  const request = (repository) => ({ type: repositoryActionTypes.R_ALL_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_ALL_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_ALL_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.getAll(name).then(
      response => dispatch(success(response.data.data)),
      error => dispatch(failure(error)),
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
}

export default repositoryActions
