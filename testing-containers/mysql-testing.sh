#!/usr/bin/env bash

USERNAME=root
PASSWORD=mddv
DATABASE=test
PORT=3306
VOLUME=./mysql-data

docker run --name $1 -e MYSQL_ROOT_PASSWORD=$PASSWORD -e MYSQL_DATABASE=$DATABASE -p $PORT:3306 -v $VOLUME:/var/lib/mysql -d mysql:5.7
