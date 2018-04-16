#!/usr/bin/env bash

docker build -t mddv-commons .
docker run -v mvn-repo:/root/.m2 --name api-commons -d mddv-commons
