import React from 'react'
import PropTypes from 'prop-types'

import './DivSqlRelation.scss'

const DivSqlRelation = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivSqlRelation.propTypes = {
  prop: PropTypes.string,
}

DivSqlRelation.defaultProps = {
  prop: '',
}

export default DivSqlRelation
