import React from 'react'
import tableIcon from 'resources/table.svg'

import './SvgSqlTable.scss'

const SvgSqlTable = ({
  name,
  columns,
  indexes,
  primaryKeys,
  foreignKeys,
  x,
  y,
}) => {
  const rectStyle = {
    fill: '#bbbbff',
    stroke: 'black',
    strokeWidth: 1.5,
    opaciti: 0.5,
  }
  const rectStyle2 = {
    fill: '#ffffff',
    stroke: 'black',
    strokeWidth: 0,
    opaciti: 0.5,
  }
  const largestColumn = columns
    .map(column => (column.name.length + column.type.length))
    .reduce((a, b) => Math.max(a, b))
  const width = (Math.max(largestColumn, name.length) * 10) + 20
  let height = columns.length + indexes.length + primaryKeys.length + foreignKeys.length
  height = (height * 20) + 25
  const renderColumns = columns.map((column, index) => {
    const start = {
      x: x + 5,
      y: y + 45 + (index * 21),
    }
    return (
      <g key={column.name}>
        <text x={start.x} y={start.y} fill="black">
          {column.name}: {column.type}{column.length ? `(${column.length})` : ''}
        </text>
      </g>
    )
  })
  return (
    <g>
      <rect
        x={x}
        y={y}
        style={rectStyle}
        width={width}
        height={height}
        rx={5}
        ry={5}
      />
      <image xlinkHref={tableIcon} x={x + 5} y={y + 4} width={20} height={20} />
      <text x={x + 28} y={y + 18} fill="black">{name}</text>
      <path stroke="blue" d={`M${x} ${(y + 25)} L${(x + width)} ${(y + 25)}`} />
      <rect
        x={x + 1}
        y={y + 27}
        style={rectStyle2}
        width={width - 2}
        height={height - 35}
      />
      {renderColumns}
    </g>
  )
}

export default SvgSqlTable
