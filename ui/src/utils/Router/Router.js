import React from 'react'
import Repositories from '../../components/Repositories/Repositories'
import Models from '../../components/Models/Models'
import BusinessObjects from '../../components/BusinessObjects/BusinessObjects'
import Welcome from '../../components/Welcome/Welcome'

const router = (currentPage) => {
  switch (currentPage) {
    case '/repositorios':
    case '/repositorios/list':
    case '/repositorios/new':
      return <Repositories />
    case '/objetosNegocio':
      return <BusinessObjects />
    case '/modelos':
      return <Models />
    default:
      return <Welcome />
  }
}

export default router
