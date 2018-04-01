import React from 'react'
import PropTypes from 'prop-types'

import './SvgSqlRelation.scss'

const SvgSqlRelation = ({ prop }) => (
  <div>
    {prop}
  </div>
)

SvgSqlRelation.propTypes = {
  prop: PropTypes.string,
}

SvgSqlRelation.defaultProps = {
  prop: '',
}

export default SvgSqlRelation
