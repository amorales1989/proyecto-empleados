# FacturacionSegundaEntregaMorales

Proyecto Spring Boot con arquitectura de 3 capas para la gestión de empleados y proyectos.

## Descripción

Este proyecto implementa un sistema de gestión de empleados y proyectos utilizando Spring Boot y JPA. La aplicación sigue una arquitectura de 3 capas:

- **Controller**: Endpoints REST para exponer la API
- **Service**: Lógica de negocio y validaciones
- **Repository**: Acceso a datos mediante JPA

## Entidades

### Empleado
- `id`: Identificador único (Long)
- `nombre`: Nombre del empleado (String, requerido)
- `apellido`: Apellido del empleado (String, requerido)
- `email`: Email único del empleado (String, requerido, único)
- `legajo`: Legajo único del empleado (String, requerido, único)
- `departamento`: Departamento al que pertenece (String)
- `fechaIngreso`: Fecha de ingreso (LocalDateTime, se establece automáticamente)

### Proyecto
- `id`: Identificador único (Long)
- `nombre`: Nombre del proyecto (String, requerido)
- `descripcion`: Descripción del proyecto (String)

### Relación
- **ManyToMany**: Un empleado puede trabajar en múltiples proyectos y un proyecto puede tener múltiples empleados.
- Las modificaciones en cascada están configuradas para mantener la integridad referencial.

## Endpoints REST

### Empleados

- `GET /api/empleados` - Obtiene todos los empleados
- `GET /api/empleados/{id}` - Obtiene un empleado por ID
- `GET /api/empleados/email/{email}` - Obtiene un empleado por email
- `GET /api/empleados/legajo/{legajo}` - Obtiene un empleado por legajo
- `POST /api/empleados` - Crea un nuevo empleado
- `PUT /api/empleados/{id}` - Actualiza un empleado existente
- `DELETE /api/empleados/{id}` - Elimina un empleado
- `POST /api/empleados/{empleadoId}/proyectos/{proyectoId}` - Asigna un proyecto a un empleado
- `DELETE /api/empleados/{empleadoId}/proyectos/{proyectoId}` - Remueve un proyecto de un empleado

### Proyectos

- `GET /api/proyectos` - Obtiene todos los proyectos
- `GET /api/proyectos/{id}` - Obtiene un proyecto por ID
- `GET /api/proyectos/buscar/{nombre}` - Busca proyectos por nombre
- `POST /api/proyectos` - Crea un nuevo proyecto
- `PUT /api/proyectos/{id}` - Actualiza un proyecto existente
- `DELETE /api/proyectos/{id}` - Elimina un proyecto
- `POST /api/proyectos/{proyectoId}/empleados/{empleadoId}` - Asigna un empleado a un proyecto
- `DELETE /api/proyectos/{proyectoId}/empleados/{empleadoId}` - Remueve un empleado de un proyecto

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
java -jar target/FacturacionSegundaEntregaMorales.jar
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
├── FacturacionSegundaEntregaMoralesApplication.java
├── controller/
│   ├── EmpleadoController.java
│   └── ProyectoController.java
├── service/
│   ├── EmpleadoService.java
│   └── ProyectoService.java
├── repository/
│   ├── EmpleadoRepository.java
│   └── ProyectoRepository.java
└── models/
    ├── Empleado.java
    └── Proyecto.java
```

## Características

- ✅ Arquitectura de 3 capas (Controller, Service, Repository)
- ✅ JPA/Hibernate para acceso a datos
- ✅ Modificaciones en cascada configuradas
- ✅ Validaciones de negocio en la capa de servicio
- ✅ Endpoints REST documentados
- ✅ Scripts SQL de inicialización
- ✅ Código documentado con JavaDoc

## Notas

- El nombre del JAR generado es: `FacturacionSegundaEntregaMorales.jar`
- La aplicación se ejecuta en el puerto 8080 por defecto
- Los datos se inicializan automáticamente al iniciar la aplicación (si `spring.sql.init.mode=always`)

