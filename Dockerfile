FROM openjdk:21-slim
COPY target/drink-0.0.1-SNAPSHOT.jar drink.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "drink.jar"]