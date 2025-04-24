FROM openjdk:17-jdk-slim
WORKDIR /app
VOLUME /tmp
COPY target/*.jar budget-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "budget-service-0.0.1-SNAPSHOT.jar"]