import React from 'react'
import PropTypes from 'prop-types'

import './SvgDocumentHierarchy.scss'

const SvgDocumentHierarchy = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgDocumentHierarchy.propTypes = {
  prop: PropTypes.string,
}

SvgDocumentHierarchy.defaultProps = {
  prop: '',
}

export default SvgDocumentHierarchy
