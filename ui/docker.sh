#!/usr/bin/env bash

TAG=mddv-frontend
API=mddv-ui

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
docker run --name $API -d -p 80:9999 $TAG
