import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles } from '@material-ui/core'
import { title } from '../../utils'
import { entityDiagram, bluegrad } from '../../utils/Graph'
import { technicalObjectActions } from '../../redux/actions'
import './Models.scss'

class RepositoryModel extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    title('Modelos')
    this.props.dispatch(technicalObjectActions.getGraphDataFromRepository(7))
  }

  componentDidUpdate() {
    const { loadedGraphdata } = this.props
    if (loadedGraphdata) {
      const { graphdata } = this.props
      console.log(graphdata)
      const tables = graphdata.tables.map(x => ({ key: x.key, items: x.columns.map(y => ({ name: y.name, figure: 'Circle', color: bluegrad })) }))
      const relations = graphdata.relations.map(x => ({ from: x.from, to: x.to, text: '0..N', toText: '1' }))
      entityDiagram({ tables, relations, container: 'graphContainer' })
    }
  }

  render() {
    const { loadedGraphdata } = this.props
    if (!loadedGraphdata) {
      return null
    }
    return (
      <div>
        <div id="graphContainer" style={{ backgroundColor: 'white', border: 'solid 1px black', width: '100%', height: '700px', marginTop: '5em' }}>
          placeholder
        </div>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading, graphdata, loadedGraphdata } = state.technicalObjectReducer
  return { loading, graphdata, loadedGraphdata }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(RepositoryModel)))
