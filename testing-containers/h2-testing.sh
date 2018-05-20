#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=1521
SECPORT=81
VOLUME=./h2-data

docker run -d -p $PORT:1521 -p $SECPORT:81 -v $VOLUME:/opt/h2-data --name=$1 oscarfonts/h2
