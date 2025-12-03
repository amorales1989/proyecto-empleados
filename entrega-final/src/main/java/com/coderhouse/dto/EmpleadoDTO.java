package com.coderhouse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear o actualizar un Empleado.
 */
public class EmpleadoDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    
    @NotBlank(message = "El legajo es obligatorio")
    @Size(min = 3, max = 20, message = "El legajo debe tener entre 3 y 20 caracteres")
    private String legajo;
    
    @Size(max = 50, message = "El departamento no puede exceder 50 caracteres")
    private String departamento;
    
    // Constructores
    
    public EmpleadoDTO() {
    }
    
    public EmpleadoDTO(String nombre, String apellido, String email, String legajo, String departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.legajo = legajo;
        this.departamento = departamento;
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLegajo() {
        return legajo;
    }
    
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
