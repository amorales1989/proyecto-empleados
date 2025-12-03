package com.coderhouse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.dto.ProyectoDTO;
import com.coderhouse.dto.ProyectoResponseDTO;
import com.coderhouse.exception.ResourceNotFoundException;
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
     * @return Lista de todos los proyectos como DTOs
     */
    public List<ProyectoResponseDTO> obtenerTodos() {
        return proyectoRepository.findAll().stream()
            .map(ProyectoResponseDTO::new)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca un proyecto por su ID.
     * 
     * @param id ID del proyecto
     * @return ProyectoResponseDTO con los datos del proyecto
     * @throws ResourceNotFoundException Si el proyecto no existe
     */
    public ProyectoResponseDTO obtenerPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", id));
        return new ProyectoResponseDTO(proyecto);
    }
    
    /**
     * Busca un proyecto por su nombre.
     * 
     * @param nombre Nombre del proyecto
     * @return ProyectoResponseDTO con los datos del proyecto
     * @throws ResourceNotFoundException Si el proyecto no existe
     */
    public ProyectoResponseDTO obtenerPorNombre(String nombre) {
        Proyecto proyecto = proyectoRepository.findByNombre(nombre)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "nombre", nombre));
        return new ProyectoResponseDTO(proyecto);
    }
    
    /**
     * Busca proyectos que contengan el nombre dado.
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de proyectos encontrados como DTOs
     */
    public List<ProyectoResponseDTO> buscarPorNombre(String nombre) {
        return proyectoRepository.findByNombreContainingIgnoreCase(nombre).stream()
            .map(ProyectoResponseDTO::new)
            .collect(Collectors.toList());
    }
    
    /**
     * Crea un nuevo proyecto.
     * 
     * @param proyectoDTO DTO con los datos del proyecto a crear
     * @return ProyectoResponseDTO del proyecto creado
     */
    public ProyectoResponseDTO crear(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto(
            proyectoDTO.getNombre(),
            proyectoDTO.getDescripcion()
        );
        
        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);
        return new ProyectoResponseDTO(proyectoGuardado);
    }
    
    /**
     * Actualiza un proyecto existente.
     * 
     * @param id ID del proyecto a actualizar
     * @param proyectoDTO DTO con los datos actualizados del proyecto
     * @return ProyectoResponseDTO del proyecto actualizado
     * @throws ResourceNotFoundException Si el proyecto no existe
     */
    public ProyectoResponseDTO actualizar(Long id, ProyectoDTO proyectoDTO) {
        Proyecto proyectoExistente = proyectoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", id));
        
        proyectoExistente.setNombre(proyectoDTO.getNombre());
        proyectoExistente.setDescripcion(proyectoDTO.getDescripcion());
        
        Proyecto proyectoActualizado = proyectoRepository.save(proyectoExistente);
        return new ProyectoResponseDTO(proyectoActualizado);
    }
    
    /**
     * Elimina un proyecto por su ID.
     * Las relaciones con empleados se actualizan en cascada.
     * 
     * @param id ID del proyecto a eliminar
     * @throws ResourceNotFoundException Si el proyecto no existe
     */
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proyecto", "id", id);
        }
        proyectoRepository.deleteById(id);
    }
    
    /**
     * Asigna un empleado a un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return ProyectoResponseDTO del proyecto actualizado
     * @throws ResourceNotFoundException Si el proyecto o empleado no existen
     */
    public ProyectoResponseDTO asignarEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", proyectoId));
        
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", empleadoId));
        
        if (!proyecto.getEmpleados().contains(empleado)) {
            proyecto.getEmpleados().add(empleado);
            empleado.getProyectos().add(proyecto);
            empleadoRepository.save(empleado);
        }
        
        Proyecto proyectoActualizado = proyectoRepository.save(proyecto);
        return new ProyectoResponseDTO(proyectoActualizado);
    }
    
    /**
     * Remueve un empleado de un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return ProyectoResponseDTO del proyecto actualizado
     * @throws ResourceNotFoundException Si el proyecto o empleado no existen
     */
    public ProyectoResponseDTO removerEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new ResourceNotFoundException("Proyecto", "id", proyectoId));
        
        Empleado empleado = empleadoRepository.findById(empleadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", empleadoId));
        
        proyecto.getEmpleados().remove(empleado);
        empleado.getProyectos().remove(proyecto);
        empleadoRepository.save(empleado);
        
        Proyecto proyectoActualizado = proyectoRepository.save(proyecto);
        return new ProyectoResponseDTO(proyectoActualizado);
    }
}

