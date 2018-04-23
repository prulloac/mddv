import RepositoryService from '../../services/RepositoryService'
import repositoryActionTypes from '../action-types/repository-action-types'

const create = ({ location = '', name = '', outsourced = false, type = '', token }) => {
  const request = (repository) => ({ type: repositoryActionTypes.C_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.C_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.C_REPO_FAILURE, payload: error })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.create({ location, name, outsourced, type, token }).then(
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
      response => dispatch(success(response)),
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
      response => dispatch(success(response)),
      error => dispatch(failure(error)),
    )
  }
}

const getAll = (name = '') => {
  const request = (repository) => ({ type: repositoryActionTypes.R_REPO, payload: repository })
  const success = (repository) => ({ type: repositoryActionTypes.R_REPO_SUCCESS, payload: repository })
  const failure = (error) => ({ type: repositoryActionTypes.R_REPO_FAILURE, payload: error })
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
}

export default repositoryActions
