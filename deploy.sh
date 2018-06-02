#!/usr/bin/env bash

ROOT_DIR=$PWD

for f in commons main mysql arango h2 mongo postgres
do
  echo "running script mddv/api-$f/docker.sh"
  cd $ROOT_DIR/api-$f
  sh docker.sh
done

cd $ROOT_DIR/testing-containers
for d in mysql arango h2 mongo postgres
do
  echo "running $d database container"
  sh testingcontainers.sh $d-testing
done
