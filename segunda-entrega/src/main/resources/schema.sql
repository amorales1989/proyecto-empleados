-- Script de creación de esquema de base de datos
-- Proyecto: FacturacionSegundaEntregaMorales
-- Base de datos: java_coderhouse_2

-- Eliminar tablas si existen (en orden inverso por dependencias)
DROP TABLE IF EXISTS proyecto_empleado;
DROP TABLE IF EXISTS Proyectos;
DROP TABLE IF EXISTS Empleados;

-- Crear tabla Empleados
CREATE TABLE IF NOT EXISTS Empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Apellido VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Legajo VARCHAR(255) NOT NULL UNIQUE,
    Departamento VARCHAR(255),
    FechaIngreso DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla Proyectos
CREATE TABLE IF NOT EXISTS Proyectos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Descripcion TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla de relación ManyToMany: proyecto_empleado
CREATE TABLE IF NOT EXISTS proyecto_empleado (
    proyecto_id BIGINT NOT NULL,
    empleado_id BIGINT NOT NULL,
    PRIMARY KEY (proyecto_id, empleado_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (empleado_id) REFERENCES Empleados(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_empleado_email ON Empleados(Email);
CREATE INDEX idx_empleado_legajo ON Empleados(Legajo);
CREATE INDEX idx_proyecto_nombre ON Proyectos(Nombre);

