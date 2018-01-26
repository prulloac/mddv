import React from 'react'
import { Grid, Header, Image, Form, Segment, Button } from 'semantic-ui-react'
import img from 'utils/Img'

import './LoginPage.scss'

const LoginPage = () => (
  <div className="login-form">
    <Grid
      textAlign="center"
      style={{ height: '100%' }}
      verticalAlign="middle"
      >
      <Grid.Column style={{ maxWidth: 450 }}>
        <Header as="h2" color="teal" textAlign="center">
          <Image src={img.mddvLogo} />
          MDDV
        </Header>
        <Form size="large">
          <Segment stacked>
            <Header as="h3" color="teal" textAlign="left">
              Inicio de Sesión
            </Header>
            <Form.Input
              fluid
              icon="user"
              iconPosition="left"
              placeholder="Correo electrónico"
            />
            <Form.Input
              fluid
              icon="lock"
              iconPosition="left"
              placeholder="Contraseña"
              type="password"
            />
            <Button color="teal" fluid size="large">Iniciar Sesión</Button>
          </Segment>
        </Form>
      </Grid.Column>
    </Grid>
  </div>
)

export default LoginPage
