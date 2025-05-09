# Sistema de Citas Médicas - Hospital

Aplicación web desarrollada en Java con Spring Boot, que permite registrar y consultar citas para médicos del área de Medicina Interna. 

Incluye frontend con Thymeleaf y ejecución en contenedor Docker.

---

## 🚀 Funcionalidades

- Registrar nuevas citas con validaciones de horario, consultorio y disponibilidad.
- Consultar citas por fecha, doctor o consultorio.
- Editar o cancelar citas.
- Cargar datos iniciales (consultorios, médicos, pacientes).

---

## 🧱 Tecnologías

- Java 17
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 (o MySQL) como base de datos
- Docker

---

## 🐳 Cómo correr con Docker

### 🔧 1. Construir el jar con Gradle:
```bash
./gradlew bootJar

🐳 2. Crear imagen Docker:
docker build -t consultorio-app .

 3. Correr contenedor:

docker run -p 8080:8080 consultorio-app

🌐 Acceder a la app:

http://localhost:8080/swagger-ui/index.html#/
