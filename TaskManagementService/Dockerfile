FROM openjdk:17-jdk-slim
WORKDIR /app
VOLUME /tmp
COPY target/*.jar task-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "task-service-0.0.1-SNAPSHOT.jar"]