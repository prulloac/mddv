FROM maven:3.3-jdk-8

COPY ./pom.xml /usr/src/parent

WORKDIR /usr/src/parent

RUN mvn clean install
