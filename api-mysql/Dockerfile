FROM mddv-commons:latest

COPY ./ /usr/src/api

WORKDIR /usr/src/api

EXPOSE 8081

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
