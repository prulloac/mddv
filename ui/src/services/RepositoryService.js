import httpClient from './HttpClient'

const create = ({
  location = '',
  name = '',
  outsourced = true,
  engine = '',
  version = '',
}) =>
  httpClient.post({
    endpoint: 'repositories',
    data: {
      location,
      name,
      outsourced,
      engine,
      version,
    },
  })

const update = ({
  id = 0,
  location = '',
  name = '',
  outsourced = true,
  engine = '',
  version = '',
}) =>
  httpClient.put({
    endpoint: '/repositories',
    data: {
      id,
      location,
      name,
      outsourced,
      engine,
      version,
    },
  })

const del = (id = 0) =>
  httpClient.delete({
    endpoint: `repositories/?id=${id}`,
  })

const findById = (id = 0) => httpClient.get({ endpoint: `repositories/${id}` })

const findByName = (name = 0) => httpClient.get({ endpoint: `repositories?name=${name}` })

const getAll = () => httpClient.get({ endpoint: 'repositories?showAll=true' })

const getConnectionParams = (id = 0) => httpClient.get({ endpoint: `repositories/connectionParams?id=${id}` })

const getExtractorCompatibles = () => httpClient.get({ endpoint: 'repositories/extractableTypes' })

const extractFromRepository = (id = 0) => httpClient.post({ endpoint: `repositories?id=${id}&extract=true` })

const updateConnectionParams = (id = 0, params = {}) => httpClient.post({ endpoint: `repositories/connectionParams?id=${id}`, data: params })

const testRepositoryConnection = (id = 0) => httpClient.post({ endpoint: `repositories/testConnection?id=${id}` })

const RepositoryService = {
  create,
  findById,
  findByName,
  getAll,
  update,
  delete: del,
  getConnectionParams,
  getExtractorCompatibles,
  extractFromRepository,
  updateConnectionParams,
  testRepositoryConnection,
}

export default RepositoryService
