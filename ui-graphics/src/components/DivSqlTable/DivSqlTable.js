import React from 'react'
import PropTypes from 'prop-types'

import './DivSqlTable.scss'

const DivSqlTable = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivSqlTable.propTypes = {
  prop: PropTypes.string,
}

DivSqlTable.defaultProps = {
  prop: '',
}

export default DivSqlTable
