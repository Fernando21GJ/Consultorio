# Dockerfile
FROM openjdk:17-jdk-slim

# Metadata
LABEL maintainer="tu-correo@ejemplo.com"

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR generado
COPY build/libs/consultorios-api-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
