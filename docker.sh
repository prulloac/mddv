#!/usr/bin/env bash

docker build -t mddv-base .
docker run -v mvn-repo:/root/.m2 --name mddv-base -d mddv-base
