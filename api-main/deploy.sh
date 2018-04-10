#!/usr/bin/env bash

FOLDER=api-main
JARFILE=api-main-0.0.1-SNAPSHOT-spring-boot.jar
TAG=api-main

sshpass -p "Llavero.21" scp -r ./target/${JARFILE} root@188.166.34.128:~/${FOLDER}/target/${JARFILE} 1>&- 2>&- &

sshpass -p "Llavero.21" scp -r ./Dockerfile root@188.166.34.128:~/$FOLDER/Dockerfile 1>&- 2>&- &

sshpass -p "Llavero.21" ssh root@188.166.34.128 "cd ~/$FOLDER && docker build -t $TAG . && docker run --name $TAG $TAG"
