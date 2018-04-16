FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv/mongo

COPY ./ /usr/src/mddv/mongo

WORKDIR /usr/src/mddv/mongo

EXPOSE 8084

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
