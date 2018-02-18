import React from 'react'

import SvgSqlRepository from 'components/SvgSqlRepository'

import repoData from 'resources/example.json'

import './App.scss'


const App = () => (
  <div>
    <svg width="1400" height="1000">
      <SvgSqlRepository
        data={repoData.data}
        name={repoData.name}
        type={repoData.type}
      />
    </svg>
  </div>
)

export default App
