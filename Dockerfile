# Uses the Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Sets the working directory
WORKDIR /app

# Copy the application jar file to the container
COPY target/*.jar app.jar

# Exposing port 8080
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]