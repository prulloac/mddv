import React from 'react'
import PropTypes from 'prop-types'

import './DivDocumentRepository.scss'

const DivDocumentRepository = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivDocumentRepository.propTypes = {
  prop: PropTypes.string,
}

DivDocumentRepository.defaultProps = {
  prop: '',
}

export default DivDocumentRepository
