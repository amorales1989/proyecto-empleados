package com.coderhouse.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.coderhouse.models.Empleado;

/**
 * DTO de respuesta para Empleado.
 * Incluye información de los proyectos asociados.
 */
public class EmpleadoResponseDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String legajo;
    private String departamento;
    private LocalDateTime fechaIngreso;
    private List<ProyectoSimpleDTO> proyectos;
    
    // Constructores
    
    public EmpleadoResponseDTO() {
    }
    
    public EmpleadoResponseDTO(Empleado empleado) {
        this.id = empleado.getId();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.legajo = empleado.getLegajo();
        this.departamento = empleado.getDepartamento();
        this.fechaIngreso = empleado.getFechaIngreso();
        this.proyectos = empleado.getProyectos().stream()
            .map(ProyectoSimpleDTO::new)
            .collect(Collectors.toList());
    }
    
    // Getters y Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public List<ProyectoSimpleDTO> getProyectos() {
        return proyectos;
    }
    
    public void setProyectos(List<ProyectoSimpleDTO> proyectos) {
        this.proyectos = proyectos;
    }
}
