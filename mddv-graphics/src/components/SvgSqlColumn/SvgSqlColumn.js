import React from 'react'
import PropTypes from 'prop-types'

import './SvgSqlColumn.scss'

const SvgSqlColumn = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgSqlColumn.propTypes = {
  prop: PropTypes.string,
}

SvgSqlColumn.defaultProps = {
  prop: '',
}

export default SvgSqlColumn
