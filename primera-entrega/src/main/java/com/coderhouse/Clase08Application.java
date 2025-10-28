package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Empleado;
import com.coderhouse.models.Proyecto;

@SpringBootApplication
public class Clase08Application implements CommandLineRunner {
    
    @Autowired
    private DaoFactory dao;

    public static void main(String[] args) {
        SpringApplication.run(Clase08Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        try {
            
            Proyecto proyecto1 = new Proyecto("Sistema de Ventas", "Desarrollo de plataforma e-commerce");
            Proyecto proyecto2 = new Proyecto("App Mobile Banking", "Aplicación móvil para banca digital");
            Proyecto proyecto3 = new Proyecto("ERP Empresarial", "Sistema integrado de gestión");
            Proyecto proyecto4 = new Proyecto("Portal Educativo", "Plataforma de cursos online");
            Proyecto proyecto5 = new Proyecto("API REST", "Servicios web para integración");
            
            Empleado empleado1 = new Empleado("María", "González", "maria.gonzalez@empresa.com", "EMP001", "Desarrollo");
            Empleado empleado2 = new Empleado("Carlos", "Rodríguez", "carlos.rodriguez@empresa.com", "EMP002", "Frontend");
            Empleado empleado3 = new Empleado("Ana", "Martínez", "ana.martinez@empresa.com", "EMP003", "Backend");
            Empleado empleado4 = new Empleado("Luis", "Fernández", "luis.fernandez@empresa.com", "EMP004", "DevOps");
            Empleado empleado5 = new Empleado("Sofía", "López", "sofia.lopez@empresa.com", "EMP005", "QA");
            
            dao.persistirEmpleado(empleado1);
            dao.persistirEmpleado(empleado2);
            dao.persistirEmpleado(empleado3);
            dao.persistirEmpleado(empleado4);
            dao.persistirEmpleado(empleado5);
            
            dao.persistirProyecto(proyecto1);
            dao.persistirProyecto(proyecto2);
            dao.persistirProyecto(proyecto3);
            dao.persistirProyecto(proyecto4);
            dao.persistirProyecto(proyecto5);
            
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}