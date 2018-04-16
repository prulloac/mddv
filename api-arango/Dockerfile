FROM mddv-commons:latest

RUN mkdir -p /usr/src/mddv/arango

COPY ./ /usr/src/mddv/arango

WORKDIR /usr/src/mddv/arango

EXPOSE 8082

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
