#!/usr/bin/env bash

ROOT_DIR=$PWD
APIS=commons main mysql arango h2 mongo postgres
DBS=mysql arango h2 mongo postgres

for f in $APIS
do
  echo "running script mddv/api-$f/docker.sh"
  cd $ROOT_DIR/api-$f
  sh docker.sh
done

cd $ROOT_DIR/testing-containers
for d in $DBS
do
  echo "running $d database container"
  sh testingcontainers.sh $d-testing
done

echo "\n\nDEPLOYMENT DONE\n\n"