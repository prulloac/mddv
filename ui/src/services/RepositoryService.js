import httpClient from './HttpClient'

const create = ({
  location = '',
  name = '',
  outsourced = true,
  type = '',
  version = '',
}) =>
  httpClient.post({
    endpoint: 'repositories',
    data: {
      location,
      name,
      outsourced,
      type,
      version,
    },
  })

const update = ({
  id = 0,
  location = '',
  name = '',
  outsourced = true,
  type = '',
  version = '',
}) =>
  httpClient.put({
    endpoint: '/repositories',
    data: {
      id,
      location,
      name,
      outsourced,
      type,
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
}

export default RepositoryService
