FROM openjdk:18-slim AS builder

# Set working directory for the build process
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the application source code
COPY . .

# Build the application with Maven 
RUN mvn clean package

# Create a slimmer image for production
FROM openjdk:18-slim AS runner

# Set the working directory for the application
WORKDIR /app

# Copy the JAR file from the builder stage to the working directory
COPY --from=builder /app/target/*.jar ./app.jar
COPY --from=builder /app/target/*.jar ./app/app.jar

# List contents of the working directory (for debugging)
RUN ls -al

# Expose the port where your Spring Boot application listens (replace 8080 with your actual port)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]