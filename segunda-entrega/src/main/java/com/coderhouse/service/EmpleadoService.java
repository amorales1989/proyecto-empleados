package com.coderhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.models.Empleado;
import com.coderhouse.models.Proyecto;
import com.coderhouse.repository.EmpleadoRepository;
import com.coderhouse.repository.ProyectoRepository;

/**
 * Servicio que contiene la lógica de negocio para la entidad Empleado.
 * Implementa operaciones CRUD y gestión de relaciones con Proyectos.
 */
@Service
@Transactional
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private ProyectoRepository proyectoRepository;
    
    /**
     * Obtiene todos los empleados.
     * 
     * @return Lista de todos los empleados
     */
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }
    
    /**
     * Busca un empleado por su ID.
     * 
     * @param id ID del empleado
     * @return Optional con el empleado encontrado
     */
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }
    
    /**
     * Busca un empleado por su email.
     * 
     * @param email Email del empleado
     * @return Optional con el empleado encontrado
     */
    public Optional<Empleado> obtenerPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }
    
    /**
     * Busca un empleado por su legajo.
     * 
     * @param legajo Legajo del empleado
     * @return Optional con el empleado encontrado
     */
    public Optional<Empleado> obtenerPorLegajo(String legajo) {
        return empleadoRepository.findByLegajo(legajo);
    }
    
    /**
     * Crea un nuevo empleado.
     * Valida que el email y legajo sean únicos.
     * 
     * @param empleado Empleado a crear
     * @return Empleado creado
     * @throws IllegalArgumentException Si el email o legajo ya existen
     */
    public Empleado crear(Empleado empleado) {
        if (empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado con el email: " + empleado.getEmail());
        }
        if (empleadoRepository.existsByLegajo(empleado.getLegajo())) {
            throw new IllegalArgumentException("Ya existe un empleado con el legajo: " + empleado.getLegajo());
        }
        return empleadoRepository.save(empleado);
    }
    
    /**
     * Actualiza un empleado existente.
     * 
     * @param id ID del empleado a actualizar
     * @param empleado Datos actualizados del empleado
     * @return Empleado actualizado
     * @throws IllegalArgumentException Si el empleado no existe o hay conflictos de email/legajo
     */
    public Empleado actualizar(Long id, Empleado empleado) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));
        
        // Validar email único si cambió
        if (!empleadoExistente.getEmail().equals(empleado.getEmail()) && 
            empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado con el email: " + empleado.getEmail());
        }
        
        // Validar legajo único si cambió
        if (!empleadoExistente.getLegajo().equals(empleado.getLegajo()) && 
            empleadoRepository.existsByLegajo(empleado.getLegajo())) {
            throw new IllegalArgumentException("Ya existe un empleado con el legajo: " + empleado.getLegajo());
        }
        
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }
    
    /**
     * Elimina un empleado por su ID.
     * Las relaciones con proyectos se actualizan en cascada.
     * 
     * @param id ID del empleado a eliminar
     * @throws IllegalArgumentException Si el empleado no existe
     */
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new IllegalArgumentException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }
    
    /**
     * Asigna un proyecto a un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado
     * @throws IllegalArgumentException Si el empleado o proyecto no existen
     */
    public Empleado asignarProyecto(Long empleadoId, Long proyectoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));
        
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + proyectoId));
        
        if (!empleado.getProyectos().contains(proyecto)) {
            empleado.getProyectos().add(proyecto);
            proyecto.getEmpleados().add(empleado);
            proyectoRepository.save(proyecto);
        }
        
        return empleadoRepository.save(empleado);
    }
    
    /**
     * Remueve un proyecto de un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado
     * @throws IllegalArgumentException Si el empleado o proyecto no existen
     */
    public Empleado removerProyecto(Long empleadoId, Long proyectoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));
        
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + proyectoId));
        
        empleado.getProyectos().remove(proyecto);
        proyecto.getEmpleados().remove(empleado);
        proyectoRepository.save(proyecto);
        
        return empleadoRepository.save(empleado);
    }
}

