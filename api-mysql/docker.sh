#!/usr/bin/env bash

docker build -t mddv-mysql .
docker run -v mvn-repo:/root/.m2 --name api-mysql -d mddv-mysql
