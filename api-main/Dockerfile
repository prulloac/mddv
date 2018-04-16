FROM mddv-commons:latest

RUN mkdir -p /usr/src/mddv/main

COPY ./ /usr/src/mddv/main

WORKDIR /usr/src/mddv/main

EXPOSE 8080

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
