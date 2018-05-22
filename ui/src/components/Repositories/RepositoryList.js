import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import { Card, Button } from 'react-materialize'
import repositoryActions from '../../redux/actions/repository-actions'

class RepositoryList extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(repositoryActions.resetRepository())
  }

  render() {
    const { loadedRepositories, repositories } = this.props
    if (!loadedRepositories) {
      return null
    }
    const repositoryCards = repositories.map((repository) => (
      <Card title={repository.name} key={repository.name}>
        {repository.type}
        <div>
          <Button className="green" node={Link} to={`/repository/edit/${repository.id}`}>Ver</Button>
          <Button className="red" node={Link} to={`/repository/delete/${repository.id}`}>Eliminar</Button>
        </div>
      </Card>
    ))
    return (
      <div>{repositoryCards}</div>
    )
  }
}

const mapStateToProps = state => {
  const { loadedRepositories, repositories } = state.repositoryReducer
  return { loadedRepositories, repositories }
}

export default withRouter(connect(mapStateToProps)(RepositoryList))
