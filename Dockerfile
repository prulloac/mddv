FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv

COPY ./pom.xml /usr/src/mddv

WORKDIR /usr/src/mddv

CMD [ "mvn", "clean", "install" ]