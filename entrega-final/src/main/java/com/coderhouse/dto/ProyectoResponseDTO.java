package com.coderhouse.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coderhouse.models.Proyecto;

/**
 * DTO de respuesta para Proyecto.
 * Incluye información de los empleados asociados.
 */
public class ProyectoResponseDTO {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private List<EmpleadoSimpleDTO> empleados;
    
    // Constructores
    
    public ProyectoResponseDTO() {
    }
    
    public ProyectoResponseDTO(Proyecto proyecto) {
        this.id = proyecto.getId();
        this.nombre = proyecto.getNombre();
        this.descripcion = proyecto.getDescripcion();
        this.empleados = proyecto.getEmpleados().stream()
            .map(EmpleadoSimpleDTO::new)
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
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<EmpleadoSimpleDTO> getEmpleados() {
        return empleados;
    }
    
    public void setEmpleados(List<EmpleadoSimpleDTO> empleados) {
        this.empleados = empleados;
    }
}
