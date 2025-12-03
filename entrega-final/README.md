# FacturacionEntregaFinalMorales

Proyecto Spring Boot con arquitectura de 3 capas para la gestión de empleados y proyectos.

## Descripción

Este proyecto implementa un sistema de gestión de empleados y proyectos utilizando Spring Boot y JPA. La aplicación sigue una arquitectura de 3 capas con las siguientes mejoras para la entrega final:

- **Controller**: Endpoints REST con documentación Swagger/OpenAPI
- **Service**: Lógica de negocio con DTOs y validaciones
- **Repository**: Acceso a datos mediante JPA
- **DTOs**: Separación entre entidades y objetos de transferencia
- **Exception Handling**: Manejo global de excepciones personalizado
- **Validation**: Validaciones con Bean Validation

## Entidades

### Empleado
- `id`: Identificador único (Long)
- `nombre`: Nombre del empleado (String, requerido, 2-50 caracteres)
- `apellido`: Apellido del empleado (String, requerido, 2-50 caracteres)
- `email`: Email único del empleado (String, requerido, único, formato email válido)
- `legajo`: Legajo único del empleado (String, requerido, único, 3-20 caracteres)
- `departamento`: Departamento al que pertenece (String, máximo 50 caracteres)
- `fechaIngreso`: Fecha de ingreso (LocalDateTime, se establece automáticamente)

### Proyecto
- `id`: Identificador único (Long)
- `nombre`: Nombre del proyecto (String, requerido, 3-100 caracteres)
- `descripcion`: Descripción del proyecto (String, máximo 500 caracteres)

### Relación
- **ManyToMany**: Un empleado puede trabajar en múltiples proyectos y un proyecto puede tener múltiples empleados.
- Las modificaciones en cascada están configuradas para mantener la integridad referencial.

## Endpoints REST

### Empleados

- `GET /api/empleados` - Obtiene todos los empleados
- `GET /api/empleados/{id}` - Obtiene un empleado por ID
- `GET /api/empleados/email/{email}` - Obtiene un empleado por email
- `GET /api/empleados/legajo/{legajo}` - Obtiene un empleado por legajo
- `POST /api/empleados` - Crea un nuevo empleado (requiere validación)
- `PUT /api/empleados/{id}` - Actualiza un empleado existente (requiere validación)
- `DELETE /api/empleados/{id}` - Elimina un empleado
- `POST /api/empleados/{empleadoId}/proyectos/{proyectoId}` - Asigna un proyecto a un empleado
- `DELETE /api/empleados/{empleadoId}/proyectos/{proyectoId}` - Remueve un proyecto de un empleado

### Proyectos

- `GET /api/proyectos` - Obtiene todos los proyectos
- `GET /api/proyectos/{id}` - Obtiene un proyecto por ID
- `GET /api/proyectos/buscar/{nombre}` - Busca proyectos por nombre
- `POST /api/proyectos` - Crea un nuevo proyecto (requiere validación)
- `PUT /api/proyectos/{id}` - Actualiza un proyecto existente (requiere validación)
- `DELETE /api/proyectos/{id}` - Elimina un proyecto
- `POST /api/proyectos/{proyectoId}/empleados/{empleadoId}` - Asigna un empleado a un proyecto
- `DELETE /api/proyectos/{proyectoId}/empleados/{empleadoId}` - Remueve un empleado de un proyecto

## Documentación API

La documentación interactiva de la API está disponible mediante Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Requisitos

- Java 21
- Maven 3.6+
- MySQL 8.0+

## Configuración

1. Crear la base de datos MySQL:
   ```sql
   CREATE DATABASE java_coderhouse_2;
   ```

2. Configurar las credenciales en `application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=tu_password
   ```

3. Ejecutar el script de inicialización (opcional):
   ```bash
   mysql -u root -p java_coderhouse_2 < src/main/resources/database-init.sql
   ```

## Compilación y Ejecución

### Compilar el proyecto
```bash
mvn clean package
```

### Ejecutar el JAR
```bash
java -jar target/FacturacionEntregaFinalMorales.jar
```

### Ejecutar con Maven
```bash
mvn spring-boot:run
```

## Scripts SQL

El proyecto incluye tres scripts SQL:

1. **schema.sql**: Script de creación de esquema (tablas e índices)
2. **data.sql**: Script de inicialización de datos (se ejecuta automáticamente)
3. **database-init.sql**: Script completo para inicialización manual de la base de datos

## Estructura del Proyecto

```
src/main/java/com/coderhouse/
├── FacturacionEntregaFinalMoralesApplication.java
├── config/
│   └── SwaggerConfig.java
├── controller/
│   ├── EmpleadoController.java
│   └── ProyectoController.java
├── service/
│   ├── EmpleadoService.java
│   └── ProyectoService.java
├── repository/
│   ├── EmpleadoRepository.java
│   └── ProyectoRepository.java
├── models/
│   ├── Empleado.java
│   └── Proyecto.java
├── dto/
│   ├── EmpleadoDTO.java
│   ├── EmpleadoResponseDTO.java
│   ├── EmpleadoSimpleDTO.java
│   ├── ProyectoDTO.java
│   ├── ProyectoResponseDTO.java
│   └── ProyectoSimpleDTO.java
└── exception/
    ├── ResourceNotFoundException.java
    ├── DuplicateResourceException.java
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java
```

## Características

### Entrega Final - Nuevas Características

- ✅ **DTOs (Data Transfer Objects)**: Separación entre entidades de base de datos y objetos de transferencia
- ✅ **Validaciones**: Bean Validation con anotaciones (@Valid, @NotBlank, @Email, @Size, etc.)
- ✅ **Manejo de Excepciones**: 
  - Excepciones personalizadas (ResourceNotFoundException, DuplicateResourceException)
  - GlobalExceptionHandler con @RestControllerAdvice
  - Respuestas de error estructuradas (ErrorResponse)
- ✅ **Documentación API**: 
  - Swagger/OpenAPI 3.0
  - Anotaciones @Operation, @ApiResponse
  - Interfaz Swagger UI interactiva
- ✅ **Códigos HTTP apropiados**: 
  - 200 OK, 201 Created, 204 No Content
  - 400 Bad Request, 404 Not Found, 409 Conflict
  - 500 Internal Server Error

### Características Base

- ✅ Arquitectura de 3 capas (Controller, Service, Repository)
- ✅ JPA/Hibernate para acceso a datos
- ✅ Modificaciones en cascada configuradas
- ✅ Validaciones de negocio en la capa de servicio
- ✅ Endpoints REST documentados
- ✅ Scripts SQL de inicialización
- ✅ Código documentado con JavaDoc

## Ejemplos de Uso

### Crear un Empleado

```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@example.com",
    "legajo": "EMP001",
    "departamento": "IT"
  }'
```

### Crear un Proyecto

```bash
curl -X POST http://localhost:8080/api/proyectos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Proyecto Alpha",
    "descripcion": "Desarrollo de nueva plataforma"
  }'
```

### Respuesta de Error (Ejemplo)

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Empleado no encontrado con id: '999'",
  "path": "/api/empleados/999"
}
```

## Notas

- El nombre del JAR generado es: `FacturacionEntregaFinalMorales.jar`
- La aplicación se ejecuta en el puerto 8080 por defecto
- Los datos se inicializan automáticamente al iniciar la aplicación (si `spring.sql.init.mode=always`)
- La documentación Swagger está disponible en: http://localhost:8080/swagger-ui.html

