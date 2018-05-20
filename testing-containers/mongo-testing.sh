#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=27017
VOLUME=./mongodb-data

docker run --name $1 -e MONGO_INITDB_ROOT_USERNAME=$USERNAME -e MONGO_INITDB_ROOT_PASSWORD=$PASSWORD -v $VOLUME:/data/db -p $PORT:27017 -d mongo:3.4
