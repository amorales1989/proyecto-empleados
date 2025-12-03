package com.coderhouse.dto;

import com.coderhouse.models.Proyecto;

/**
 * DTO simplificado de Proyecto para evitar referencias circulares.
 */
public class ProyectoSimpleDTO {
    
    private Long id;
    private String nombre;
    private String descripcion;
    
    // Constructores
    
    public ProyectoSimpleDTO() {
    }
    
    public ProyectoSimpleDTO(Proyecto proyecto) {
        this.id = proyecto.getId();
        this.nombre = proyecto.getNombre();
        this.descripcion = proyecto.getDescripcion();
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
}
