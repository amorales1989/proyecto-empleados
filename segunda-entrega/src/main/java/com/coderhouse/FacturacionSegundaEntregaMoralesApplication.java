package com.coderhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Proyecto: FacturacionSegundaEntregaMorales
 * 
 * Esta aplicación implementa un sistema de gestión de empleados y proyectos
 * utilizando arquitectura de 3 capas:
 * - Controller: Endpoints REST
 * - Service: Lógica de negocio
 * - Repository: Acceso a datos con JPA
 */
@SpringBootApplication
public class FacturacionSegundaEntregaMoralesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturacionSegundaEntregaMoralesApplication.class, args);
    }
}

