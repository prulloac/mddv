#!/usr/bin/env bash

TAG=mddv-frontend
API=mddv-ui

if docker container ls | grep $API > /dev/null; then
  echo "stopping old container"
  docker container stop $API
fi

if docker container ls -a | grep $API > /dev/null; then
  echo "removing old container"
  docker container rm $API
fi

if docker image ls -a | grep $TAG > /dev/null; then
  echo "removing old image"
  docker rmi $TAG
fi

echo "building image"
docker build -t $TAG .

echo "launching container"
docker run --name $API -d -p 80:9999 $TAG
