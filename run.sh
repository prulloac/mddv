#!/usr/bin/env bash

### Compiles everything
mvn clean install

### Start main api
cd api-main
mvn clean install spring-boot:run

### Start frontend
