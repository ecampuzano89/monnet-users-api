# monnet-users-api

API REST construida con Spring Boot que consume la API pública de [JSONPlaceholder](https://jsonplaceholder.typicode.com/users), persiste los usuarios en una base H2 en memoria y expone los datos protegidos con JWT.

## Requisitos

- Java 11
- Maven 3.8+

## Construcción

```bash
mvn clean package
```

## Tests y cobertura

Ejecutar tests y generar reporte de JaCoCo:

```bash
mvn clean test jacoco:report
```

El reporte HTML queda disponible en `target/site/jacoco/index.html`.

Verificar que el proyecto cumple el gate del 85% de cobertura:

```bash
mvn verify
```

## Ejecución local

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

## Endpoints principales

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/auth/login` | Obtiene un token JWT | No |
| POST | `/users/sync` | Consume JSONPlaceholder y sincroniza usuarios | JWT |
| GET | `/users` | Lista los usuarios persistidos | JWT |
| GET | `/actuator/health` | Health check de Actuator | No |
| GET | `/actuator/info` | Información de la aplicación | No |
| GET | `/swagger-ui.html` | Documentación interactiva OpenAPI | No |

## Docker

Construir la imagen (requiere el jar previamente generado):

```bash
mvn clean package -DskipTests
docker build -t monnet-users-api .
```

Ejecutar el contenedor:

```bash
docker run -p 8080:8080 monnet-users-api
```

## Stack

- Spring Boot 2.7.3
- Spring Data JPA + H2
- Spring Security + JWT
- Spring Boot Actuator
- SpringDoc OpenAPI
- MapStruct
- Lombok
- JaCoCo
