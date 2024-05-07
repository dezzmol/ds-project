FROM gradle:6.9.4-jdk11
ENTRYPOINT ["java", "-jar", "/app.jar"]

COPY build/libs/project-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080