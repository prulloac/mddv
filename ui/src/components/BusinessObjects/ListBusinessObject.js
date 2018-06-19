import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import { Card, Button, CardContent, Typography, CardActions, Icon } from '@material-ui/core'
import { businessObjectActions } from '../../redux/actions'

class BusinessObjectsList extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
    this.props.dispatch(businessObjectActions.resetObject())
    this.props.dispatch(businessObjectActions.getAll())
  }

  handleDelete = (id = 0) => (event) => {
    event.preventDefault()
    const { dispatch } = this.props
    if (id > 0) {
      dispatch(businessObjectActions.delete(id))
      dispatch(businessObjectActions.getAll())
    }
  }

  render() {
    const newButton = (
      <div className="mddv-buttons" align="right">
        <Button variant="raised" component={Link} to="/business/new">
          Nuevo Objeto
        </Button>
      </div>
    )
    const { loadedBusinessObjects, businessObjects } = this.props
    if (!loadedBusinessObjects) {
      return newButton
    }
    const repositoryCards = businessObjects.map((object) => (
      <Card className="mddv-repo-card" key={object.id}>
        <CardContent>
          <Typography variant="headline">{object.name} ({object.version || 'Sin Versión'})</Typography>
          <Typography>Tipo: {object.type}</Typography>
          <Typography>Descripción: {object.description}</Typography>
        </CardContent>
        <CardActions>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/business/edit/${object.id}`}
          >
            Editar
            <Icon className="icon-button">edit</Icon>
          </Button>
          <Button
            size="small"
            variant="raised"
            color="primary"
            component={Link}
            to={`/business/relations/${object.id}`}
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
      <div>{newButton}{repositoryCards}</div>
    )
  }
}

const mapStateToProps = state => {
  const { loadedBusinessObjects, businessObjects } = state.businessObjectReducer
  return { loadedBusinessObjects, businessObjects }
}

export default withRouter(connect(mapStateToProps)(BusinessObjectsList))
