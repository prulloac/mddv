#!/usr/bin/env bash

TAG=mddv-arangodb
API=api-arangodb
PORT=8082

echo "building image"
docker build -t mddv-mysql .

if docker container ls | grep $API > 1; then
  echo "stopping old container"
  docker container stop $API
fi

if docker container ls -a | grep $API > 1; then
  echo "removing old container"
  docker container rm $API
fi

echo "launching container"
docker run -v mvn-repo:/root/.m2 --name $API -d -p $PORT:$PORT $TAG
