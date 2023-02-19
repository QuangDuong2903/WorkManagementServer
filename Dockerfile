#
# Build stage
#
#FROM maven:3.6.0-jdk-11 AS build

#COPY . .

#RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
#FROM openjdk:11-jdk-slim

#COPY --from=build /target/workmanagement-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8081

#ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:11-jdk-slim

COPY target/workmanagement-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]