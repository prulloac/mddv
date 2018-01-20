import React from 'react'

import './Dashboard.scss'

const Dashboard = ({ match }) => (
  <div>
    <h2>Dashboard {match.path}</h2>
  </div>
)

export default Dashboard
