import RepositoryService from '../../services/RepositoryService'
import repositoryActionTypes from '../action-types/repository-action-types'

const create = ({
  location = '',
  name = '',
  outsourced = false,
  type = '',
  token,
}) => {
  const request = (repository) => ({
    type: repositoryActionTypes.CREATE_REPOSITORY,
    payload: repository,
  })
  const success = (repository) => ({
    type: repositoryActionTypes.CREATE_REPOSITORY_SUCCESS,
    payload: repository,
  })
  const failure = (error) => ({
    type: repositoryActionTypes.CREATE_REPOSITORY_FAILURE,
    payload: error,
  })
  return dispatch => {
    dispatch(request(name))
    RepositoryService.create({
      location,
      name,
      outsourced,
      type,
      token,
    }).then(
      response => dispatch(success(response)),
      error => dispatch(failure(error)),
    )
  }
}

const get = (id) => {
  const request = (repository) => ({
    type: repositoryActionTypes.LOAD_REPOSITORY,
    payload: repository,
  })
  const success = (repository) => ({
    type: repositoryActionTypes.LOAD_REPOSITORY_SUCCESS,
    payload: repository,
  })
  const failure = (error) => ({
    type: repositoryActionTypes.LOAD_REPOSITORY_FAILURE,
    payload: error,
  })
  return dispatch => {
    dispatch(request(id))
    RepositoryService.get(id)
      .then(
        response => dispatch(success(response)),
        error => dispatch(failure(error)),
      )
  }
}

const repositoryActions = {
  create,
  get,
}

export default repositoryActions
