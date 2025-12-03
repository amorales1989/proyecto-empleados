-- Script completo de inicialización de base de datos
-- Proyecto: FacturacionSegundaEntregaMorales
-- Este script puede ejecutarse manualmente para inicializar la base de datos

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS java_coderhouse_2;
USE java_coderhouse_2;

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

-- Insertar empleados
INSERT INTO Empleados (Nombre, Apellido, Email, Legajo, Departamento, FechaIngreso) VALUES
('María', 'González', 'maria.gonzalez@empresa.com', 'EMP001', 'Desarrollo', NOW()),
('Carlos', 'Rodríguez', 'carlos.rodriguez@empresa.com', 'EMP002', 'Frontend', NOW()),
('Ana', 'Martínez', 'ana.martinez@empresa.com', 'EMP003', 'Backend', NOW()),
('Luis', 'Fernández', 'luis.fernandez@empresa.com', 'EMP004', 'DevOps', NOW()),
('Sofía', 'López', 'sofia.lopez@empresa.com', 'EMP005', 'QA', NOW());

-- Insertar proyectos
INSERT INTO Proyectos (Nombre, Descripcion) VALUES
('Sistema de Ventas', 'Desarrollo de plataforma e-commerce'),
('App Mobile Banking', 'Aplicación móvil para banca digital'),
('ERP Empresarial', 'Sistema integrado de gestión'),
('Portal Educativo', 'Plataforma de cursos online'),
('API REST', 'Servicios web para integración');

-- Asignar empleados a proyectos (relaciones ManyToMany)
-- María González trabaja en Sistema de Ventas y App Mobile Banking
INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES
(1, 1), (2, 1);

-- Carlos Rodríguez trabaja en App Mobile Banking y Portal Educativo
INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES
(2, 2), (4, 2);

-- Ana Martínez trabaja en ERP Empresarial y API REST
INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES
(3, 3), (5, 3);

-- Luis Fernández trabaja en Sistema de Ventas y ERP Empresarial
INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES
(1, 4), (3, 4);

-- Sofía López trabaja en Portal Educativo y API REST
INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES
(4, 5), (5, 5);

