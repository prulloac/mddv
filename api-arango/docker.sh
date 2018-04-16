#!/usr/bin/env bash

docker build -t mddv-arango .
docker run -v mvn-repo:/root/.m2 --name api-arango -d mddv-arango
