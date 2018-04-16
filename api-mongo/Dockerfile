FROM mddv-commons:latest

RUN mkdir -p /usr/src/mddv/mongo

COPY ./ /usr/src/mddv/mongo

WORKDIR /usr/src/mddv/mongo

EXPOSE 8084

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
