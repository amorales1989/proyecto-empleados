package com.coderhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Empleados y Proyectos")
                        .version("3.0")
                        .description(
                                "API REST para la gestión de empleados y proyectos con arquitectura de 3 capas, DTOs, validaciones y manejo de excepciones")
                        .contact(new Contact()
                                .name("Alejandro Morales")
                                .email("alejandro@coderhouse.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
