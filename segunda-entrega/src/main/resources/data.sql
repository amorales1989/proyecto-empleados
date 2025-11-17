-- Script de inicialización de datos
-- Proyecto: FacturacionSegundaEntregaMorales
-- Este script se ejecuta automáticamente al iniciar la aplicación si spring.jpa.hibernate.ddl-auto está configurado

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

