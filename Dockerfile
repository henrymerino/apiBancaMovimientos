FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/bank-api-mov.jar bank-api.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","bank-api.jar"]