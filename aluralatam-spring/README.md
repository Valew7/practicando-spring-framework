# ForoHub API

API RESTful para la gestión de un sistema de foros. Este proyecto fue desarrollado como parte de la especialización de Backend con Spring Boot de Alura Latam.

## Estado del Proyecto

🚧 **En desarrollo** 🚧

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.x**: Framework base.
  - Spring Web: Para la construcción de la API REST.
  - Spring Data JPA: Para el mapeo ORM y repositorios.
  - Spring Security: Para el aseguramiento de la API.
  - Spring Validation: Para control y validación de entrada de datos.
- **MySQL 8+**: Base de datos relacional.
- **Flyway**: Control de versiones y migraciones de la base de datos.
- **Lombok**: Para reducir el código repetitivo o boilerplate.
- **Auth0 java-jwt (4.4.0)**: Generación y validación de tokens JWT.

## Características Principales

- **[x] CRUD de Tópicos**: Crear, listar, leer detalles, actualizar y eliminar tópicos (borrado lógico).
- **[x] Autenticación y Autorización**: Protegido con Spring Security y tokens JWT (JSON Web Tokens). Filtro de request para validar al usuario en cada petición.
- **[x] Paginación y Ordenamiento**: Los listados se envían en formatos de páginas ordenables gracias a Spring Data.
- **[x] Migraciones de Base de Datos**: Inicialización y control histórico automático con Flyway.
- **[x] Manejo de Excepciones Global**: Clases y respuestas estandarizadas usando `@RestControllerAdvice`.

## Configuración y Ejecución Local

### Prerrequisitos
- JDK 17 o superior.
- Base de datos MySQL instalada y corriendo en el puerto 3306.
- (Opcional) Maven instalado (el proyecto incluye el wrapper de Maven `mvnw`).

### Pasos

1. **Clonar el Repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd aluralatam-spring
   ```

2. **Configurar la Base de Datos**
   Abre tu gestor de base de datos MySQL y crea un esquema llamado `forohub`.
   ```sql
   CREATE DATABASE forohub;
   ```

3. **Variables de Entorno (o application.properties)**
   Asegúrate de que las credenciales en `src/main/resources/application.properties` coincidan con tu MySQL local. Además, define una clave secreta JWT en tu entorno o deja la por defecto:
   ```properties
   spring.datasource.username=TU_USUARIO_MYSQL
   spring.datasource.password=TU_PASSWORD_MYSQL
   JWT_SECRET=tu-clave-secreta-para-jwt
   ```

4. **Ejecutar el proyecto**
   En la raíz del proyecto, corre:
   ```bash
   ./mvnw spring-boot:run
   ```
   *Nota: En Windows puedes usar `mvnw.cmd spring-boot:run`*

La base de datos será construida y poblada con la estructura automáticamente gracias a Flyway.

## Endpoints Principales y Ejemplos

*(Nota: Para acceder a `/topicos` necesitarás adjuntar el Header HTTP: `Authorization: Bearer <token>` que te devuelve `/login`)*

### Autenticar / Login (`POST /login`)

**Petición:**
```json
{
  "login": "usuario@ejemplo.com",
  "password": "123"
}
```

**Respuesta [HTTP 200 OK]:**
```json
{
  "jwtToken": "eyJhbGciOiJIUz... (token JWT)"
}
```

### Crear Tópico (`POST /topicos`)

**Petición:**
```json
{
  "titulo": "Duda sobre Spring Security",
  "mensaje": "¿Cómo configuro el filtro Stateless?",
  "autorId": 1,
  "cursoId": 1
}
```

**Respuesta [HTTP 201 Created]:** *(devuelve también URL en la cabecera Location)*
```json
{
  "id": 1,
  "titulo": "Duda sobre Spring Security",
  "mensaje": "¿Cómo configuro el filtro Stateless?",
  "fechaCreacion": "2024-03-01T10:00:00",
  "status": "ABIERTO",
  "autorId": 1,
  "cursoId": 1
}
```

### Listar Tópicos Activos (`GET /topicos?size=10&page=0`)

**Respuesta [HTTP 200 OK]:**
```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Duda sobre Spring Security",
      "mensaje": "¿Cómo configuro el filtro Stateless?",
      "fechaCreacion": "2024-03-01T10:00:00",
      "status": "ABIERTO",
      "autorId": 1,
      "cursoId": 1
    }
  ],
  "pageable": { ... },
  "totalPages": 1,
  "totalElements": 1,
  "size": 10,
  "number": 0,
  ...
}
```

### Actualizar Tópico (`PUT /topicos`)

*Requiere enviar JSON con ID, título y/o mensaje y/o status.*

### Eliminar Tópico Lógicamente (`DELETE /topicos/{id}`)

*Retorna HTTP 204 No Content en caso de éxito.*

## Estructura de Carpetas

```
aluralatam-spring/
├── src/main/
│   ├── java/com/aluracursos/forohub/
│   │   ├── controller/      (Controladores REST)
│   │   ├── domain/          (Entidades, Repositorios JPA y DTOs Records separadas por dominio /curso, /topico, /usuario)
│   │   ├── infra/errores/   (Clases de validación y @RestControllerAdvice para errores globales)
│   │   ├── security/        (Filtros JWT, Configuración de Spring Security, Token Service)
│   │   └── ForohubApplication.java
│   └── resources/
│       ├── db/migration/    (Scripts SQL de Flyway)
│       └── application.properties
├── pom.xml                  (Configuración de MAVEN)
└── README.md
```

## Licencia y Autor

Desarrollado para el Challange / Reto ForoHub de Alura Latam.

Autor: El usuario / desarrollador de la máquina local. Todos los derechos reservados bajo propósitos de estudio.
