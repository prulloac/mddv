FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv/postgres

COPY ./ /usr/src/mddv/postgres

WORKDIR /usr/src/mddv/postgres

EXPOSE 8083

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
