FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/microservice-gym-0.0.1-SNAPSHOT.jar secondary.jar
ENTRYPOINT ["java", "-jar","/secondary.jar"]

