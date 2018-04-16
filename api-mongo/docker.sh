#!/usr/bin/env bash

docker build -t mddv-mongo .
docker run -v mvn-repo:/root/.m2 --name api-mongo -d mddv-mongo
