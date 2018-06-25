import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import { Card, Button, CardContent, Typography, CardActions, Icon } from '@material-ui/core'
import Eye from 'mdi-material-ui/Eye'
import { technicalObjectActions } from '../../redux/actions'

class TechnicalObjectsList extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(technicalObjectActions.resetObject())
    if (this.props.match.params.id === undefined) {
      this.props.dispatch(technicalObjectActions.getRepositories())
    } else {
      this.props.dispatch(technicalObjectActions.getChildren(this.props.match.params.id))
    }
  }

  handleDelete = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch, match } = this.props
    if (id > 0) {
      dispatch(technicalObjectActions.delete(id))
      if (match.params.id === undefined) {
        dispatch(technicalObjectActions.getRepositories())
      } else {
        dispatch(technicalObjectActions.getChildren(match.params.id))
      }
    }
  }

  handleRefresh = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch } = this.props
    if (id > 0) {
      dispatch(technicalObjectActions.getChildren(id))
    }
  }

  render() {
    const newButton = (
      <div className="mddv-buttons" align="right">
        <Button variant="raised" component={Link} to="/technical/new">
          Nuevo Objeto
        </Button>
      </div>
    )
    const { loadedTechnicalObjects, technicalObjects } = this.props
    if (!loadedTechnicalObjects) {
      return newButton
    }
    const cards = technicalObjects.map((object) => (
      <Card className="mddv-repo-card" key={object.id}>
        <CardContent>
          <Button
            mini
            variant="fab"
            color="primary"
            style={{ color: '#fff', marginTop: '0px', marginRight: '0px', position: 'relative', float: 'right' }}
            onClick={this.handleRefresh(object.id)}
          >
            <Eye />
          </Button>
          <Typography variant="headline">{object.name} ({object.version || 'Sin Versión'})</Typography>
          <Typography variant="body2">Tipo:</Typography>
          <Typography variant="body1">{object.type}</Typography>
          <Typography variant="body2">Descripción:</Typography>
          <Typography variant="body1">{object.description}</Typography>
        </CardContent>
        <CardActions>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/technical/edit/${object.id}`}
          >
            Editar
            <Icon className="icon-button">edit</Icon>
          </Button>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/technical/relations/${object.id}`}
            style={{ color: '#fff' }}
          >
            Relaciones
            <Icon className="icon-button">security</Icon>
          </Button>
          <Button
            size="small"
            variant="raised"
            color="secondary"
            onClick={this.handleDelete(object.id)}
          >
            Eliminar
            <Icon className="icon-button">delete</Icon>
          </Button>
        </CardActions>
      </Card>
    ))
    return (
      <div>{newButton}{cards}</div>
    )
  }
}

const mapStateToProps = state => {
  const { loadedTechnicalObjects, technicalObjects } = state.technicalObjectReducer
  return { loadedTechnicalObjects, technicalObjects }
}

export default withRouter(connect(mapStateToProps)(TechnicalObjectsList))
