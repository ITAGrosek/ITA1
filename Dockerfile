# Use the official Java 17 image from Docker Hub as the base image
FROM openjdk:17

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
