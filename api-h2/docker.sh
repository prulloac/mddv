#!/usr/bin/env bash

docker build -t mddv-h2 .
docker run -v mvn-repo:/root/.m2 --name api-h2 -d mddv-h2
