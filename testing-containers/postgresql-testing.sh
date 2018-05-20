#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=5432
VOLUME=$PWD/data/postgres

docker run --name $1 -e POSTGRES_PASSWORD=$PASSWORD -e POSTGRES_USER=$USERNAME -e POSTGRES_DB=$DATABASE -v $VOLUME:/var/lib/postgresql/data -p $PORT:5432 -d postgres:10.4
