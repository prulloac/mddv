FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv/h2

COPY ./ /usr/src/mddv/h2

WORKDIR /usr/src/mddv/h2

EXPOSE 8083

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
