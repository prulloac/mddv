FROM maven:3.3-jdk-8

COPY ./ /usr/src/api

WORKDIR /usr/src/api

EXPOSE 8082

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
