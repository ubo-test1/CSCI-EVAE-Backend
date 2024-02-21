# Stage 1: Build the application
FROM gradle:8.5.0-jdk21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Copy the source code
COPY src src

# Build the application
RUN gradle build -Pwar -x test

# Expose the port that the application will run on
EXPOSE 9091

# Define the command to run your application
CMD ["java", "-jar", "build/libs/csa-back-0.0.1-SNAPSHOT.war"]