# Use a base image with Java and a JRE
FROM openjdk:20-ea-4-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/bank-0.0.1-SNAPSHOT.jar .

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "bank-0.0.1-SNAPSHOT.jar"]

