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

const get = (id) => httpClient.get({ endpoint: `/repositories/${id}` })

const RepositoryService = {
  create,
  get,
}

export default RepositoryService
