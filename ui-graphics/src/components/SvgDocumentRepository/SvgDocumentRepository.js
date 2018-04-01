import React from 'react'
import PropTypes from 'prop-types'

import './SvgDocumentRepository.scss'

const SvgDocumentRepository = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgDocumentRepository.propTypes = {
  prop: PropTypes.string,
}

SvgDocumentRepository.defaultProps = {
  prop: '',
}

export default SvgDocumentRepository
