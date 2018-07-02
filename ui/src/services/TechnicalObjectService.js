import httpClient from './HttpClient'

const create = ({
  name = '',
  description = '',
  type = '',
  version = '',
  parentId = 0,
}) =>
  httpClient.post({
    endpoint: `technical-objects/create?parent=${parentId}`,
    data: {
      name,
      description,
      type,
      version,
    },
  })

const update = ({
  id = 0,
  name = '',
  description = '',
  type = '',
  version = '',
}) =>
  httpClient.put({
    endpoint: '/technical-objects',
    data: {
      id,
      name,
      description,
      type,
      version,
    },
  })

const del = (id = 0) =>
  httpClient.delete({
    endpoint: `technical-objects/?id=${id}`,
  })

const findById = (id = 0) => httpClient.get({ endpoint: `technical-objects/${id}` })

const findByName = (name = 0) => httpClient.get({ endpoint: `technical-objects?name=${name}` })

const getAll = () => httpClient.get({ endpoint: 'technical-objects?showAll=true' })

const getTypes = ({ parentId } = { parentId: 0 }) => httpClient.get({ endpoint: `technical-objects/types?parentId=${parentId}` })

const getRepositories = () => httpClient.get({ endpoint: 'technical-objects/repositories' })

const getChildren = (id = 0) => httpClient.get({ endpoint: `technical-objects/children?parentId=${id}` })

const getGraphDataFromRepository = (id = 0) => httpClient.get({ endpoint: `technical-objects/repository?id=${id}` })

const TechnicalObjectService = {
  create,
  findById,
  findByName,
  getAll,
  update,
  delete: del,
  getTypes,
  getRepositories,
  getChildren,
  getGraphDataFromRepository,
}

export default TechnicalObjectService
