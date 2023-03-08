FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/costumer-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "costumer-0.0.1-SNAPSHOT.jar"]