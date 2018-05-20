#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=8529
VOLUME=./arangodb-data

docker run --name $1 -e ARANGO_ROOT_PASSWORD=$PASSWORD -v $VOLUME:/var/lib/arangodb3 -p $PORT:8529 -d arangodb/arangodb:3.1
