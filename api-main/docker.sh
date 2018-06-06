#!/usr/bin/env bash

TAG=mddv-main
API=api-main
PORT=8080

echo "building image"
docker build -t $TAG .

if docker container ls | grep $API > /dev/null; then
  echo "stopping old container"
  docker container stop $API
fi

if docker container ls -a | grep $API > /dev/null; then
  echo "removing old container"
  docker container rm $API
fi

echo "launching container"
docker run -v mvn-repo:/root/.m2 -v h2-main:/root/h2Databases --name $API -d -p $PORT:$PORT $TAG
