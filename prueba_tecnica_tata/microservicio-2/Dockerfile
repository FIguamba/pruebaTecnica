# Etapa 1: Compilación con Maven
FROM maven:3.9.1-openjdk-21-slim AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y las fuentes del proyecto
COPY pom.xml ./
COPY src ./src/

# Compilar el proyecto con Maven (esto generará el archivo JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final para ejecutar el servicio
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa anterior
COPY --from=builder /app/target/microservicio-2.jar cuentas.jar

# Exponer el puerto donde la aplicación escuchará
EXPOSE 8083

# Comando para ejecutar la aplicación Java
ENTRYPOINT ["java", "-jar", "cuentas.jar"]
