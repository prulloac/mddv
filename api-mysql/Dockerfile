FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv/mysql

COPY ./ /usr/src/mddv/mysql

WORKDIR /usr/src/mddv/mysql

EXPOSE 8081

CMD [ "mvn", "clean", "install", "spring-boot:run" ]
