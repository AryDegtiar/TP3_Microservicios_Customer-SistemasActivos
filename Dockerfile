#FROM openjdk:17-jdk-alpine
#
#WORKDIR /app
#
#COPY target/costumer-0.0.1-SNAPSHOT.jar .
#
#ENV SPRING_DATASOURCE_URL=jdbc:mysql://uk6uvzwqnfo6139d:VnImjK9miylDRqh3AuLi@bm0bdzlz9oak5nzjpvlv-mysql.services.clever-cloud.com:3306/bm0bdzlz9oak5nzjpvlv
#ENV SPRING_DATASOURCE_USERNAME=uk6uvzwqnfo6139d
#ENV SPRING_DATASOURCE_PASSWORD=uk6uvzwqnfo6139d
#ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
#ENV SERVER_PORT=8081
#
#CMD ["java", "-jar", "costumer-0.0.1-SNAPSHOT.jar"]

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/costumer-0.0.1-SNAPSHOT.jar .

ENV SPRING_DATASOURCE_URL=jdbc:mysql://uk6uvzwqnfo6139d:VnImjK9miylDRqh3AuLi@bm0bdzlz9oak5nzjpvlv-mysql.services.clever-cloud.com:3306/bm0bdzlz9oak5nzjpvlv
ENV SPRING_DATASOURCE_USERNAME=uk6uvzwqnfo6139d
ENV SPRING_DATASOURCE_PASSWORD=uk6uvzwqnfo6139d
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

EXPOSE 8081

CMD ["java", "-jar", "costumer-0.0.1-SNAPSHOT.jar", "--server.port=8081", "--spring.application.name=ms-customer-container", "--spring.hostname=$HOSTNAME"]
