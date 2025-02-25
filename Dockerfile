# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Install necessary GUI dependencies
RUN apt-get update && apt-get install -y libx11-6 libxext6 libxrender1 libxtst6 libxi6

# Set the working directory
WORKDIR /app

# Copy the compiled JAR
COPY target/ScientificCalculator-1.0-SNAPSHOT.jar app.jar

# Set DISPLAY variable for GUI forwarding
ENV DISPLAY=:0

# Run the GUI application
CMD ["java", "-jar", "app.jar"]
