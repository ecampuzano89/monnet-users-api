FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

COPY target/users-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
