#!/usr/bin/env bash

CONTAINERS=$@

for f in CONTAINERS
do
  if docker container ls | grep $f > /dev/null; then
    echo "stopping old $f container"
    docker container stop $f
  fi

  if docker container ls -a | grep $f > /dev/null; then
    echo "removing old $f container"
    docker container rm $f
  fi
  echo "running $f"
  dos2unix ./$f.sh
  sh ./$f.sh $f
done
