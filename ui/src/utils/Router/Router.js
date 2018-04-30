import React from 'react'
import Repositories from '../../components/Repositories/Repositories'
import Models from '../../components/Models/Models'
import BusinessObjects from '../../components/BusinessObjects/BusinessObjects'
import Welcome from '../../components/Welcome/Welcome'

const router = (currentPage) => {
  switch (currentPage) {
    case (currentPage.match(/repositorios(|\/delete\/\d|edit\/\d|list|new)/) || {}).input:
      return <Repositories />
    case (currentPage.match(/objetosNegocio(|\/delete\/\d|edit\/\d|list|new)/) || {}).input:
      return <BusinessObjects />
    case (currentPage.match(/modelos(|\/delete\/\d|edit\/\d|list|new)/) || {}).input:
      return <Models />
    case (currentPage.match(/usuarios(|\/delete\/\d|edit\/\d|list|new)/) || {}).input:
      return <Welcome />
    default:
      return <Welcome />
  }
}

export default router
