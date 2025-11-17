package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/**
 * Entidad que representa un Empleado en el sistema.
 * Mantiene una relación ManyToMany con Proyecto.
 */
@Entity
@Table(name = "Empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Column(name = "Apellido", nullable = false)
    private String apellido;
    
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "Legajo", nullable = false, unique = true)
    private String legajo;
    
    @Column(name = "Departamento")
    private String departamento;
    
    @Column(name = "FechaIngreso")
    private LocalDateTime fechaIngreso;
    
    @ManyToMany(mappedBy = "empleados", fetch = FetchType.LAZY)
    private List<Proyecto> proyectos = new ArrayList<>();
    
    /**
     * Constructor por defecto requerido por JPA.
     */
    public Empleado() {
        super();
    }
    
    /**
     * Constructor con parámetros principales.
     * 
     * @param nombre Nombre del empleado
     * @param apellido Apellido del empleado
     * @param email Email único del empleado
     * @param legajo Legajo único del empleado
     * @param departamento Departamento al que pertenece
     */
    public Empleado(String nombre, String apellido, String email, String legajo, String departamento) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.legajo = legajo;
        this.departamento = departamento;
    }

    /**
     * Método que se ejecuta antes de persistir la entidad.
     * Establece la fecha de ingreso si no está definida.
     */
    @PrePersist
    protected void onCreate() {
        if (fechaIngreso == null) {
            fechaIngreso = LocalDateTime.now();
        }
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

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + 
               ", email=" + email + ", legajo=" + legajo + ", departamento=" + departamento + 
               ", fechaIngreso=" + fechaIngreso + "]";
    }
}

