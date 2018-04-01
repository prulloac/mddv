import React from 'react'
import PropTypes from 'prop-types'

import './DivBusinessObject.scss'

const DivBusinessObject = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivBusinessObject.propTypes = {
  prop: PropTypes.string,
}

DivBusinessObject.defaultProps = {
  prop: '',
}

export default DivBusinessObject
