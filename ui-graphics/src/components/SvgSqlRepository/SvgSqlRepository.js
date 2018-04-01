import React from 'react'

import SvgSqlTable from 'components/SvgSqlTable'

import './SvgSqlRepository.scss'

const SvgSqlRepository = ({ data, name, type }) => {
  const { tables } = data
  const renderTables = tables.map((table, index) =>
    (
      <SvgSqlTable
        name={table.name}
        columns={table.columns}
        indexes={table.indexes}
        primaryKeys={table.primary_keys}
        foreignKeys={table.foreign_keys}
        x={40 + (index * 200)}
        y={60}
        key={table.name}
      />
    ))
  const repositoryStyle = {
    fill: '#eeeeff',
    stroke: 'black',
    strokeWidth: 1.5,
    opaciti: 0.5,
  }
  return (
    <g>
      <rect x={15} y={15} style={repositoryStyle} width={1000} height={500} />
      <text x={200} y={32}>{name} ({type.name}:{type.version})</text>
      {renderTables}
    </g>
  )
}


export default SvgSqlRepository
