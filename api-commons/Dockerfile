FROM mddv-base:latest

RUN mkdir -p /usr/src/mddv/commons

COPY ./ /usr/src/mddv/commons

WORKDIR /usr/src/mddv/commons

CMD ["mvn", "clean", "install"]
