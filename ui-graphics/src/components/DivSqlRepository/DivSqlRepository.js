import React from 'react'
import PropTypes from 'prop-types'

import './DivSqlRepository.scss'

const DivSqlRepository = ({ prop }) => (
  <div>
    {prop}
  </div>
)

DivSqlRepository.propTypes = {
  prop: PropTypes.string,
}

DivSqlRepository.defaultProps = {
  prop: '',
}

export default DivSqlRepository
