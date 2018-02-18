import React from 'react'
import PropTypes from 'prop-types'

import './DivSqlColumn.scss'

const DivSqlColumn = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivSqlColumn.propTypes = {
  prop: PropTypes.string,
}

DivSqlColumn.defaultProps = {
  prop: '',
}

export default DivSqlColumn
