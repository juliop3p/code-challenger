FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/insurance-policy-0.0.1-SNAPSHOT.jar insurance-policy-0.0.1-SNAPSHOT.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "insurance-policy-0.0.1-SNAPSHOT.jar"]

