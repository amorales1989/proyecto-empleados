package com.coderhouse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.dto.EmpleadoDTO;
import com.coderhouse.dto.EmpleadoResponseDTO;
import com.coderhouse.exception.DuplicateResourceException;
import com.coderhouse.exception.ResourceNotFoundException;
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
     * @return Lista de todos los empleados como DTOs
     */
    public List<EmpleadoResponseDTO> obtenerTodos() {
        return empleadoRepository.findAll().stream()
            .map(EmpleadoResponseDTO::new)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca un empleado por su ID.
     * 
     * @param id ID del empleado
     * @return EmpleadoResponseDTO con los datos del empleado
     * @throws ResourceNotFoundException Si el empleado no existe
     */
    public EmpleadoResponseDTO obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", id));
        return new EmpleadoResponseDTO(empleado);
    }
    
    /**
     * Busca un empleado por su email.
     * 
     * @param email Email del empleado
     * @return EmpleadoResponseDTO con los datos del empleado
     * @throws ResourceNotFoundException Si el empleado no existe
     */
    public EmpleadoResponseDTO obtenerPorEmail(String email) {
        Empleado empleado = empleadoRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "email", email));
        return new EmpleadoResponseDTO(empleado);
    }
    
    /**
     * Busca un empleado por su legajo.
     * 
     * @param legajo Legajo del empleado
     * @return EmpleadoResponseDTO con los datos del empleado
     * @throws ResourceNotFoundException Si el empleado no existe
     */
    public EmpleadoResponseDTO obtenerPorLegajo(String legajo) {
        Empleado empleado = empleadoRepository.findByLegajo(legajo)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "legajo", legajo));
        return new EmpleadoResponseDTO(empleado);
    }
    
    /**
     * Crea un nuevo empleado.
     * Valida que el email y legajo sean únicos.
     * 
     * @param empleadoDTO DTO con los datos del empleado a crear
     * @return EmpleadoResponseDTO del empleado creado
     * @throws DuplicateResourceException Si el email o legajo ya existen
     */
    public EmpleadoResponseDTO crear(EmpleadoDTO empleadoDTO) {
        if (empleadoRepository.existsByEmail(empleadoDTO.getEmail())) {
            throw new DuplicateResourceException("Empleado", "email", empleadoDTO.getEmail());
        }
        if (empleadoRepository.existsByLegajo(empleadoDTO.getLegajo())) {
            throw new DuplicateResourceException("Empleado", "legajo", empleadoDTO.getLegajo());
        }
        
        Empleado empleado = new Empleado(
            empleadoDTO.getNombre(),
            empleadoDTO.getApellido(),
            empleadoDTO.getEmail(),
            empleadoDTO.getLegajo(),
            empleadoDTO.getDepartamento()
        );
        
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return new EmpleadoResponseDTO(empleadoGuardado);
    }
    
    /**
     * Actualiza un empleado existente.
     * 
     * @param id ID del empleado a actualizar
     * @param empleadoDTO DTO con los datos actualizados del empleado
     * @return EmpleadoResponseDTO del empleado actualizado
     * @throws ResourceNotFoundException Si el empleado no existe
     * @throws DuplicateResourceException Si hay conflictos de email/legajo
     */
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", id));
        
        // Validar email único si cambió
        if (!empleadoExistente.getEmail().equals(empleadoDTO.getEmail()) && 
            empleadoRepository.existsByEmail(empleadoDTO.getEmail())) {
            throw new DuplicateResourceException("Empleado", "email", empleadoDTO.getEmail());
        }
        
        // Validar legajo único si cambió
        if (!empleadoExistente.getLegajo().equals(empleadoDTO.getLegajo()) && 
            empleadoRepository.existsByLegajo(empleadoDTO.getLegajo())) {
            throw new DuplicateResourceException("Empleado", "legajo", empleadoDTO.getLegajo());
        }
        
        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setApellido(empleadoDTO.getApellido());
        empleadoExistente.setEmail(empleadoDTO.getEmail());
        empleadoExistente.setLegajo(empleadoDTO.getLegajo());
        empleadoExistente.setDepartamento(empleadoDTO.getDepartamento());
        
        Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
        return new EmpleadoResponseDTO(empleadoActualizado);
    }
    
    /**
     * Elimina un empleado por su ID.
     * Las relaciones con proyectos se actualizan en cascada.
     * 
     * @param id ID del empleado a eliminar
     * @throws ResourceNotFoundException Si el empleado no existe
     */
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empleado", "id", id);
        }
        empleadoRepository.deleteById(id);
    }
    
    /**
     * Asigna un proyecto a un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return EmpleadoResponseDTO del empleado actualizado
     * @throws ResourceNotFoundException Si el empleado o proyecto no existen
     */
    public EmpleadoResponseDTO asignarProyecto(Long empleadoId, Long proyectoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", empleadoId));
        
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", proyectoId));
        
        if (!empleado.getProyectos().contains(proyecto)) {
            empleado.getProyectos().add(proyecto);
            proyecto.getEmpleados().add(empleado);
            proyectoRepository.save(proyecto);
        }
        
        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return new EmpleadoResponseDTO(empleadoActualizado);
    }
    
    /**
     * Remueve un proyecto de un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return EmpleadoResponseDTO del empleado actualizado
     * @throws ResourceNotFoundException Si el empleado o proyecto no existen
     */
    public EmpleadoResponseDTO removerProyecto(Long empleadoId, Long proyectoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", empleadoId));
        
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", proyectoId));
        
        empleado.getProyectos().remove(proyecto);
        proyecto.getEmpleados().remove(empleado);
        proyectoRepository.save(proyecto);
        
        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return new EmpleadoResponseDTO(empleadoActualizado);
    }
}

