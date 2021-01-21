ARG baseImageVersion
FROM eventuateio/eventuate-examples-docker-images-quarkus-example-base-image:$baseImageVersion
COPY build/order-history-service-*.jar service.jar
COPY build/lib/ lib/
