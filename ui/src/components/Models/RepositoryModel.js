import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Card, CardContent, Typography, CardActions, Button } from '@material-ui/core'
import DatabaseSearch from 'mdi-material-ui/DatabaseSearch'
import { title } from '../../utils'
import { entityDiagram, bluegrad } from '../../utils/Graph'
import { technicalObjectActions, repositoryActions } from '../../redux/actions'
import './Models.scss'

class RepositoryModel extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    title('Modelos')
    this.props.dispatch(repositoryActions.getAll())
  }

  componentDidUpdate() {
    const { loadedGraphdata } = this.props
    if (loadedGraphdata) {
      const { graphdata } = this.props
      const tables = graphdata.tables.map(x => ({ key: x.key, items: x.columns.map(y => ({ name: y.name, figure: 'Circle', color: bluegrad })) }))
      const relations = graphdata.relations.map(x => ({ from: x.from, to: x.to, text: '0..N', toText: '1' }))
      entityDiagram({ tables, relations, container: 'graphContainer' })
    }
  }

  handleSelect = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch } = this.props
    if (id > 0) {
      dispatch(technicalObjectActions.getGraphDataFromRepository(id))
    }
  }

  render() {
    const { repositories, loadedRepositories, loadedGraphdata } = this.props
    if (!loadedRepositories) {
      return null
    }
    const cards = repositories.map((repository) => (
      <Card className="mddv-repo-card" key={repository.id}>
        <CardContent>
          <Button
            size="small"
            variant="raised"
            color="primary"
            onClick={this.handleSelect(repository.id)}
            style={{ float: 'right', position: 'relative', marginTop: '0px', marginRight: '0px' }}
          >
            Ver Modelo
            <DatabaseSearch />
          </Button>
          <Typography variant="headline">{repository.name}</Typography>
          <Typography variant="body2">Tipo:</Typography>
          <Typography variant="body1">{repository.type} ({repository.version})</Typography>
        </CardContent>
      </Card>
    ))
    let placeholder = cards
    if (loadedGraphdata) {
      placeholder = (
        <div id="graphContainer" style={{ backgroundColor: 'white', border: 'solid 1px black', width: '100%', height: '700px', marginTop: '5em' }}>
          placeholder
        </div>)
    }
    return (
      <div>
        <div>
          {placeholder}
        </div>
      </div>
    )
  }
}

const mapStateToProps = state => {
  const { loading, graphdata, loadedGraphdata } = state.technicalObjectReducer
  const { loadedRepositories, repositories } = state.repositoryReducer
  return { loading, graphdata, loadedGraphdata, loadedRepositories, repositories }
}

export default withStyles(null)(withRouter(connect(mapStateToProps)(RepositoryModel)))
