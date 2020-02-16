FROM maven:3.6.0-jdk-11-slim AS build


COPY ./src /app/src
COPY ./pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:8-jre-alpine

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["java" ,"-DJDBC_DATABASE_URL=jdbc:postgresql://pg_db/eventsDB", "-DJDBC_DATABASE_USERNAME=eventAdmin", "-DJDBC_DATABASE_PASSWORD=app","-jar", "/app/app.jar"]