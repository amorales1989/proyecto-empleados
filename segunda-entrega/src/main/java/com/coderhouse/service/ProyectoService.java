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
 * Servicio que contiene la lógica de negocio para la entidad Proyecto.
 * Implementa operaciones CRUD y gestión de relaciones con Empleados.
 */
@Service
@Transactional
public class ProyectoService {
    
    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    /**
     * Obtiene todos los proyectos.
     * 
     * @return Lista de todos los proyectos
     */
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }
    
    /**
     * Busca un proyecto por su ID.
     * 
     * @param id ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }
    
    /**
     * Busca un proyecto por su nombre.
     * 
     * @param nombre Nombre del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyecto> obtenerPorNombre(String nombre) {
        return proyectoRepository.findByNombre(nombre);
    }
    
    /**
     * Busca proyectos que contengan el nombre dado.
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de proyectos encontrados
     */
    public List<Proyecto> buscarPorNombre(String nombre) {
        return proyectoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    /**
     * Crea un nuevo proyecto.
     * 
     * @param proyecto Proyecto a crear
     * @return Proyecto creado
     */
    public Proyecto crear(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }
    
    /**
     * Actualiza un proyecto existente.
     * 
     * @param id ID del proyecto a actualizar
     * @param proyecto Datos actualizados del proyecto
     * @return Proyecto actualizado
     * @throws IllegalArgumentException Si el proyecto no existe
     */
    public Proyecto actualizar(Long id, Proyecto proyecto) {
        if (!proyectoRepository.existsById(id)) {
            throw new IllegalArgumentException("Proyecto no encontrado con ID: " + id);
        }
        proyecto.setId(id);
        return proyectoRepository.save(proyecto);
    }
    
    /**
     * Elimina un proyecto por su ID.
     * Las relaciones con empleados se actualizan en cascada.
     * 
     * @param id ID del proyecto a eliminar
     * @throws IllegalArgumentException Si el proyecto no existe
     */
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new IllegalArgumentException("Proyecto no encontrado con ID: " + id);
        }
        proyectoRepository.deleteById(id);
    }
    
    /**
     * Asigna un empleado a un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado
     * @throws IllegalArgumentException Si el proyecto o empleado no existen
     */
    public Proyecto asignarEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + proyectoId));
        
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));
        
        if (!proyecto.getEmpleados().contains(empleado)) {
            proyecto.getEmpleados().add(empleado);
            empleado.getProyectos().add(proyecto);
            empleadoRepository.save(empleado);
        }
        
        return proyectoRepository.save(proyecto);
    }
    
    /**
     * Remueve un empleado de un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado
     * @throws IllegalArgumentException Si el proyecto o empleado no existen
     */
    public Proyecto removerEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + proyectoId));
        
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + empleadoId));
        
        proyecto.getEmpleados().remove(empleado);
        empleado.getProyectos().remove(proyecto);
        empleadoRepository.save(empleado);
        
        return proyectoRepository.save(proyecto);
    }
}

