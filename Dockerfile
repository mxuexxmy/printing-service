FROM openjdk:8-alpine

RUN apk add --no-cache fontconfig ttf-dejavu

COPY target/printing-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
