import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { withStyles, Card, CardContent, Typography, Button } from '@material-ui/core'
import DatabaseSearch from 'mdi-material-ui/DatabaseSearch'
import { diagram, bluegrad } from '../../utils/Graph'
import technicalObjectActions from '../../redux/actions/technical-object-actions'
import repositoryActions from '../../redux/actions/repository-actions'
import './Models.scss'

class RepositoryModel extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(repositoryActions.getAll())
  }

  componentDidUpdate() {
    const { loadedGraphdata } = this.props
    if (loadedGraphdata) {
      const { graphdata } = this.props
      const nodes = graphdata.nodes.map(x => ({
        key: x.name,
        category: x.category,
        items: x.items.map(y => ({ name: y.name, type: y.type })) }))
      const links = graphdata.links.map(x => ({ from: x.from, to: x.to, category: x.category }))
      diagram({ nodes, links, container: 'graphContainer' })
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
