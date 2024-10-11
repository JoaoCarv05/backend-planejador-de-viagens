FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/plannerEmailService-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]