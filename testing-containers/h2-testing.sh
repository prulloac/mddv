#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=1521
SECPORT=81
VOLUME=$PWD/data/h2

docker run -d -p $PORT:1521 -p $SECPORT:81 -v $VOLUME:/opt/h2-data --name=$1 oscarfonts/h2
