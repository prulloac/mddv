import React from 'react'
import PropTypes from 'prop-types'

import './SvgBusinessObject.scss'

const SvgBusinessObject = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgBusinessObject.propTypes = {
  prop: PropTypes.string,
}

SvgBusinessObject.defaultProps = {
  prop: '',
}

export default SvgBusinessObject
