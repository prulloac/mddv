#!/usr/bin/env bash

docker build -t mddv-main .
docker run -v mvn-repo:/root/.m2 --name api-main -p 8080:8080 -d mddv-main
