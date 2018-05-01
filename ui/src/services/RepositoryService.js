import httpClient from './HttpClient'

const create = ({
  location = '',
  name = '',
  outsourced = false,
  type = '',
  token = '',
}) =>
  httpClient.post({
    endpoint: '/repositories',
    data: {
      location,
      name,
      outsourced,
      type,
    },
    config: httpClient.auth(token),
  })

const update = ({
  id = 0,
  location = '',
  name = '',
  outsourced = false,
  type = '',
  token = '',
}) =>
  httpClient.put({
    endpoint: '/repositories',
    data: {
      id,
      location,
      name,
      outsourced,
      type,
    },
    config: httpClient.auth(token),
  })

const del = (id = 0, token = '') =>
  httpClient.delete({
    endpoint: `/repositories/?id=${id}`,
    config: httpClient.auth(token),
  })

const findById = (id = 0) => httpClient.get({ endpoint: `/repositories/${id}` })

const findByName = (name = 0) => httpClient.get({ endpoint: `/repositories?name=${name}` })

const getAll = () => httpClient.get({ endpoint: '/repositories' })

const RepositoryService = {
  create,
  findById,
  findByName,
  getAll,
  update,
  delete: del,
}

export default RepositoryService
