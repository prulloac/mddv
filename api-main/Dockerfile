FROM openjdk:8

WORKDIR /app

ADD ./target /app

VOLUME [ "/logs/:/app/logs" ]

CMD ["java", "-jar", "api-main-0.0.1-SNAPSHOT-spring-boot.jar"]

EXPOSE 8080
