import React from 'react'
import PropTypes from 'prop-types'

import './DivDocumentHierarchy.scss'

const DivDocumentHierarchy = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivDocumentHierarchy.propTypes = {
  prop: PropTypes.string,
}

DivDocumentHierarchy.defaultProps = {
  prop: '',
}

export default DivDocumentHierarchy
