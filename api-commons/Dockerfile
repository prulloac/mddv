FROM maven:3.3-jdk-8

RUN mkdir -p /usr/src/mddv/commons

COPY ./ /usr/src/mddv/commons

WORKDIR /usr/src/mddv/commons

CMD ["mvn", "clean", "install"]
