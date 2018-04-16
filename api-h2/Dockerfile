FROM mddv-commons:latest

RUN mkdir -p /usr/src/mddv/h2

COPY ./ /usr/src/mddv/h2

WORKDIR /usr/src/mddv/h2

EXPOSE 8083

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
