FROM maven:3.5.4-jdk-8-alpine AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
RUN mvn dependency:resolve
COPY src /app/src/
RUN mvn clean package

FROM openjdk:8u171-jre-alpine3.7
WORKDIR /app
COPY --from=build /app/target/cloudpizza-meat-service-1.0.0-SNAPSHOT.jar /app/
ENTRYPOINT [ "java", "-jar", "/app/cloudpizza-meat-service-1.0.0-SNAPSHOT.jar" ]
