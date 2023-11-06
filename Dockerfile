FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
COPY --from=build /target/icalgenerator-0.0.1-SNAPSHOT.jar icalgenerator.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","icalgenerator.jar"]