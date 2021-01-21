FROM openjdk:8u252-slim-buster
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY build/order-history-service-0.1.0-SNAPSHOT-runner.jar order-history-service-0.1.0-SNAPSHOT-runner.jar
COPY build/lib/ lib/
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/q/health/ready || exit 1
CMD java ${JAVA_OPTS:-"-Xmx64m"} -jar order-history-service-0.1.0-SNAPSHOT-runner.jar