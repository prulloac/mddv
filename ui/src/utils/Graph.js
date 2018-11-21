import go from 'gojs'

const $ = go.GraphObject.make

const bluegrad = $(go.Brush, 'Linear', { 0: 'rgb(0, 204, 255)', 0.5: 'rgb(86, 86, 186)', 1: 'rgb(86, 86, 186)' })
const greengrad = $(go.Brush, 'Linear', { 0: 'rgb(158, 209, 159)', 1: 'rgb(67, 101, 56)' })
const redgrad = $(go.Brush, 'Linear', { 0: 'rgb(206, 106, 100)', 1: 'rgb(180, 56, 50)' })
const yellowgrad = $(go.Brush, 'Linear', { 0: 'rgb(254, 221, 50)', 1: 'rgb(254, 182, 50)' })
const lightgrad = $(go.Brush, 'Linear', { 1: '#E6E6FA', 0: '#FFFAF0' })

const umlCompositionLink = $(
  go.Link,
  {
    selectionAdorned: true,
    layerName: 'Foreground',
    reshapable: true,
    routing: go.Link.AvoidsNodes,
    corner: 5,
    curve: go.Link.JumpOver,
  },
  $(go.Shape),
  $(
    go.Shape,
    { fromArrow: '', strokeWidth: 2 },
  ),
  $(
    go.Shape,
    { toArrow: 'StretchedDiamond', fill: 'black', strokeWidth: 2 },
  ),
)

const umlAggregationLink = $(
  go.Link,
  {
    selectionAdorned: true,
    layerName: 'Foreground',
    reshapable: true,
    routing: go.Link.AvoidsNodes,
    corner: 5,
    curve: go.Link.JumpOver,
  },
  $(go.Shape),
  $(
    go.Shape,
    { fromArrow: '', strokeWidth: 2 },
  ),
  $(
    go.Shape,
    { toArrow: 'StretchedDiamond', fill: 'white', strokeWidth: 2 },
  ),
)

const umlGeneralizationLink = $(
  go.Link,
  {
    selectionAdorned: true,
    layerName: 'Foreground',
    reshapable: true,
    routing: go.Link.AvoidsNodes,
    corner: 5,
    curve: go.Link.JumpOver,
  },
  $(go.Shape),
  $(
    go.Shape,
    { fromArrow: '', strokeWidth: 2 },
  ),
  $(
    go.Shape,
    { toArrow: 'Triangle', fill: 'white', strokeWidth: 2 },
  ),
)

const manyToOneLink = $(
  go.Link,
  {
    selectionAdorned: true,
    layerName: 'Foreground',
    reshapable: true,
    routing: go.Link.AvoidsNodes,
    corner: 5,
    curve: go.Link.JumpOver,
  },
  $(
    go.Shape,
    { stroke: '#303B45', strokeWidth: 2 },
  ),
  $(
    go.Shape,
    { fromArrow: 'BackwardFork', strokeWidth: 2 },
  ),
  $(
    go.TextBlock,
    {
      textAlign: 'center',
      font: 'bold 14px sans-serif',
      stroke: '#1967B3',
      segmentIndex: 0,
      segmentOffset: new go.Point(NaN, NaN),
      segmentOrientation: go.Link.OrientUpright,
      text: '1..N',
    },
  ),
  $(
    go.TextBlock,
    {
      textAlign: 'center',
      font: 'bold 14px sans-serif',
      stroke: '#1967B3',
      segmentIndex: -1,
      segmentOffset: new go.Point(NaN, NaN),
      segmentOrientation: go.Link.OrientUpright,
      text: '1',
    },
  ),
)

const entityColumn = $(
  go.Panel, 'Horizontal',
  $(
    go.TextBlock,
    { width: 10,
      font: 'bold 14px sans-serif',
      stroke: '#333333',
      text: '-',
    },
  ),
  $(
    go.TextBlock,
    { stroke: '#333333',
      font: 'bold 14px sans-serif' },
    new go.Binding('text', 'name'),
  ),
  $(
    go.TextBlock,
    { width: 10,
      font: 'bold 14px sans-serif',
      stroke: '#333333',
      text: ':',
    },
  ),
  $(
    go.TextBlock,
    { stroke: '#333333',
      font: 'bold 14px sans-serif' },
    new go.Binding('text', 'type'),
  ),
)

const entityTable = $(
  go.Node,
  'Auto',
  {
    selectionAdorned: true,
    resizable: true,
    layoutConditions: go.Part.LayoutStandard & ~go.Part.LayoutNodeSized,
    fromSpot: go.Spot.AllSides,
    toSpot: go.Spot.AllSides,
    isShadowed: true,
    shadowColor: '#C5C1AA' },
  new go.Binding('location', 'location').makeTwoWay(),
  new go.Binding('desiredSize', 'visible', (v) => new go.Size(NaN, NaN)).ofObject('LIST'),
  $(
    go.Shape, 'Rectangle',
    { fill: lightgrad, stroke: '#756875', strokeWidth: 3 },
  ),
  $(
    go.Panel, 'Table',
    { margin: 1, stretch: go.GraphObject.Fill },
    $(go.RowColumnDefinition, {
      row: 0,
      sizing: go.RowColumnDefinition.None,
      background: bluegrad,
    }),
    $(
      go.TextBlock,
      {
        row: 0,
        alignment: go.Spot.Center,
        margin: new go.Margin(0, 14, 0, 2),
        font: 'bold 16px sans-serif',
      },
      new go.Binding('text', 'key'),
    ),
    $(
      'PanelExpanderButton',
      'LIST',
      {
        row: 0,
        alignment: go.Spot.TopRight,
      },
    ),
    $(
      go.Panel, 'Vertical',
      {
        name: 'LIST',
        row: 1,
        padding: 3,
        alignment: go.Spot.TopLeft,
        defaultAlignment: go.Spot.Left,
        stretch: go.GraphObject.Horizontal,
        itemTemplate: entityColumn,
      },
      new go.Binding('itemArray', 'items'),
    ),
  ),
)

const umlTable = $(
  go.Node,
  'Auto',
  {
    selectionAdorned: true,
    resizable: true,
    layoutConditions: go.Part.LayoutStandard & ~go.Part.LayoutNodeSized,
    fromSpot: go.Spot.AllSides,
    toSpot: go.Spot.AllSides,
    isShadowed: true,
    shadowColor: '#C5C1AA' },
  new go.Binding('location', 'location').makeTwoWay(),
  new go.Binding('desiredSize', 'visible', (v) => new go.Size(NaN, NaN)).ofObject('LIST'),
  $(
    go.Shape, 'Rectangle',
    { fill: 'lightyellow', stroke: '#756875', strokeWidth: 3 },
  ),
  $(
    go.Panel, 'Table',
    { margin: 1, stretch: go.GraphObject.Fill },
    $(go.RowColumnDefinition, {
      row: 0,
      sizing: go.RowColumnDefinition.None,
    }),
    $(
      go.TextBlock,
      {
        row: 0,
        alignment: go.Spot.Center,
        margin: new go.Margin(0, 14, 0, 2),
        font: 'bold 16px sans-serif',
      },
      new go.Binding('text', 'key'),
    ),
    $(
      'PanelExpanderButton',
      'LIST',
      {
        row: 0,
        alignment: go.Spot.TopRight,
      },
    ),
    $(
      go.Panel, 'Vertical',
      {
        name: 'LIST',
        row: 1,
        padding: 3,
        alignment: go.Spot.TopLeft,
        defaultAlignment: go.Spot.Left,
        stretch: go.GraphObject.Horizontal,
        itemTemplate: entityColumn,
      },
      new go.Binding('itemArray', 'items'),
    ),
  ),
)

const nodeTemplates = new go.Map('string', go.Node)
nodeTemplates.add('Tabla', entityTable)
nodeTemplates.add('Colección', umlTable)

const linkTemplates = new go.Map('string', go.Link)
linkTemplates.add('Relación Muchos a Uno', manyToOneLink)
linkTemplates.add('Relación de Dependencia', umlAggregationLink)
linkTemplates.add('Relación de Uso', umlCompositionLink)
linkTemplates.add('Relación de Generalización', umlGeneralizationLink)

const diagram = ({ nodes, links, container } = { nodes: [], links: [], container: '' }) => {
  const diag = $(go.Diagram, container, {
    initialContentAlignment: go.Spot.Center,
    allowDelete: false,
    allowCopy: false,
    layout: $(go.ForceDirectedLayout),
    'undoManager.isEnabled': true,
    initialScale: 1.2,
  })
  diag.nodeTemplateMap = nodeTemplates
  diag.linkTemplateMap = linkTemplates
  diag.model = new go.GraphLinksModel(nodes, links)
}

export { diagram }
export { bluegrad }
