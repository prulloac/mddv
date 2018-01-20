import React from 'react'

import './NotFound.scss'

const NotFound = ({ match }) => (
  <div className="valign-wrapper">
    <h1 className="center-align">Página no encontrada: {match.path}</h1>
  </div>
)

export default NotFound
