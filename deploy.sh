#!/usr/bin/env bash

ROOT_DIR=$PWD

for f in api-commons api-main api-mysql api-arango api-h2 api-mongo api-postgres ui
do
  echo "running script mddv/$f/docker.sh"
  cd $ROOT_DIR/$f
  sh docker.sh
done

cd $ROOT_DIR/testing-containers
for d in mysql arango h2 mongo postgresql
do
  echo "running $d database container"
  sh testcontainers.sh $d-testing
done
