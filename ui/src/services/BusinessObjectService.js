import httpClient from './HttpClient'

const create = ({
  name = '',
  description = '',
  type = '',
  version = '',
}) =>
  httpClient.post({
    endpoint: 'business-objects',
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
    endpoint: '/business-objects',
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
    endpoint: `business-objects/?id=${id}`,
  })

const findById = (id = 0) => httpClient.get({ endpoint: `business-objects/${id}` })

const findByName = (name = 0) => httpClient.get({ endpoint: `business-objects?name=${name}` })

const getAll = () => httpClient.get({ endpoint: 'business-objects?showAll=true' })

const getTypes = () => httpClient.get({ endpoint: 'business-objects/types' })


const BusinessObjectService = {
  create,
  findById,
  findByName,
  getAll,
  update,
  delete: del,
  getTypes,
}

export default BusinessObjectService
