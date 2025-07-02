# Etapa 1: Build con Maven y Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos el proyecto y lo construimos
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera con Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos solo el JAR generado
COPY --from=builder /app/target/ABMexternos-*.jar app.jar

# Puerto en el que corre la app
EXPOSE 8088

# Activamos perfil externo si querés usarlo
ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["java", "-jar", "app.jar"]
