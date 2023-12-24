FROM openjdk:17 AS build

COPY . .
RUN chmod +x gradlew
# Run the build using Gradle Wrapper
RUN microdnf install findutils
RUN ./gradlew build

# Stage 2: Create the final image
FROM openjdk:17

WORKDIR /secondhand

COPY --from=build build/libs/*.jar secondhand.jar

# Specify the command to run on container startup
ENTRYPOINT ["java", "-jar", "secondhand.jar"]