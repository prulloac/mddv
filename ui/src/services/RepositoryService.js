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

const findById = (id = 0) => httpClient.get({ endpoint: `/repositories/${id}` })

const findByName = (name = 0) => httpClient.get({ endpoint: `/repositories?name=${name}` })

const getAll = () => httpClient.get({ endpoint: '/repositories' })

const RepositoryService = {
  create,
  findById,
  findByName,
  getAll,
}

export default RepositoryService
