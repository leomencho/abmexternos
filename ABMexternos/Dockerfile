FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY target/ABMexternos-0.0.2.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]
