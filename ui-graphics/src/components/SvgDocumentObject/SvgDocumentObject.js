import React from 'react'
import PropTypes from 'prop-types'

import './SvgDocumentObject.scss'

const SvgDocumentObject = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgDocumentObject.propTypes = {
  prop: PropTypes.string,
}

SvgDocumentObject.defaultProps = {
  prop: '',
}

export default SvgDocumentObject
