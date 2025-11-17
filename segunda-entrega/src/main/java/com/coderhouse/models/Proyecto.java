package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Proyecto en el sistema.
 * Mantiene una relación ManyToMany con Empleado.
 */
@Entity
@Table(name = "Proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Column(name = "Descripcion")
    private String descripcion;
    
    /**
     * Relación ManyToMany con Empleado.
     * Las modificaciones en cascada permiten que al eliminar un proyecto,
     * se actualice la relación con los empleados.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "proyecto_empleado",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleados = new ArrayList<>();
    
    /**
     * Constructor por defecto requerido por JPA.
     */
    public Proyecto() {
        super();
    }
    
    /**
     * Constructor con parámetros principales.
     * 
     * @param nombre Nombre del proyecto
     * @param descripcion Descripción del proyecto
     */
    public Proyecto(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }
}
