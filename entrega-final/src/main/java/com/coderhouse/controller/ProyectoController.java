package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.ProyectoDTO;
import com.coderhouse.dto.ProyectoResponseDTO;
import com.coderhouse.service.ProyectoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller REST para gestionar operaciones sobre la entidad Proyecto.
 * Expone endpoints para operaciones CRUD y gestión de relaciones con Empleados.
 */
@RestController
@RequestMapping("/api/proyectos")
@Tag(name = "Proyectos", description = "API para gestión de proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoService proyectoService;
    
    /**
     * Obtiene todos los proyectos.
     * 
     * @return Lista de todos los proyectos
     */
    @Operation(summary = "Obtener todos los proyectos", description = "Retorna una lista con todos los proyectos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de proyectos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ProyectoResponseDTO>> obtenerTodos() {
        List<ProyectoResponseDTO> proyectos = proyectoService.obtenerTodos();
        return ResponseEntity.ok(proyectos);
    }
    
    /**
     * Obtiene un proyecto por su ID.
     * 
     * @param id ID del proyecto
     * @return Proyecto encontrado
     */
    @Operation(summary = "Obtener proyecto por ID", description = "Retorna un proyecto específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto encontrado"),
        @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> obtenerPorId(@PathVariable Long id) {
        ProyectoResponseDTO proyecto = proyectoService.obtenerPorId(id);
        return ResponseEntity.ok(proyecto);
    }
    
    /**
     * Busca proyectos por nombre (búsqueda parcial, case-insensitive).
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de proyectos encontrados
     */
    @Operation(summary = "Buscar proyectos por nombre", description = "Busca proyectos que contengan el nombre especificado (búsqueda parcial)")
    @ApiResponse(responseCode = "200", description = "Lista de proyectos encontrados")
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ProyectoResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<ProyectoResponseDTO> proyectos = proyectoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(proyectos);
    }
    
    /**
     * Crea un nuevo proyecto.
     * 
     * @param proyectoDTO Datos del proyecto a crear
     * @return Proyecto creado
     */
    @Operation(summary = "Crear un nuevo proyecto", description = "Crea un nuevo proyecto con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<ProyectoResponseDTO> crear(@Valid @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoResponseDTO proyectoCreado = proyectoService.crear(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoCreado);
    }
    
    /**
     * Actualiza un proyecto existente.
     * 
     * @param id ID del proyecto a actualizar
     * @param proyectoDTO Datos actualizados del proyecto
     * @return Proyecto actualizado
     */
    @Operation(summary = "Actualizar un proyecto", description = "Actualiza los datos de un proyecto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoResponseDTO proyectoActualizado = proyectoService.actualizar(id, proyectoDTO);
        return ResponseEntity.ok(proyectoActualizado);
    }
    
    /**
     * Elimina un proyecto por su ID.
     * 
     * @param id ID del proyecto a eliminar
     * @return 204 No Content
     */
    @Operation(summary = "Eliminar un proyecto", description = "Elimina un proyecto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Asigna un empleado a un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado
     */
    @Operation(summary = "Asignar empleado a proyecto", description = "Asigna un empleado existente a un proyecto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado asignado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Proyecto o empleado no encontrado")
    })
    @PostMapping("/{proyectoId}/empleados/{empleadoId}")
    public ResponseEntity<ProyectoResponseDTO> asignarEmpleado(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        ProyectoResponseDTO proyecto = proyectoService.asignarEmpleado(proyectoId, empleadoId);
        return ResponseEntity.ok(proyecto);
    }
    
    /**
     * Remueve un empleado de un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado
     */
    @Operation(summary = "Remover empleado de proyecto", description = "Remueve la asignación de un empleado de un proyecto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado removido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Proyecto o empleado no encontrado")
    })
    @DeleteMapping("/{proyectoId}/empleados/{empleadoId}")
    public ResponseEntity<ProyectoResponseDTO> removerEmpleado(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        ProyectoResponseDTO proyecto = proyectoService.removerEmpleado(proyectoId, empleadoId);
        return ResponseEntity.ok(proyecto);
    }
}

