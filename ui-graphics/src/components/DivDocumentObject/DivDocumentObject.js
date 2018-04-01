import React from 'react'
import PropTypes from 'prop-types'

import './DivDocumentObject.scss'

const DivDocumentObject = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivDocumentObject.propTypes = {
  prop: PropTypes.string,
}

DivDocumentObject.defaultProps = {
  prop: '',
}

export default DivDocumentObject
