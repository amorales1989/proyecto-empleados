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

import com.coderhouse.dto.EmpleadoDTO;
import com.coderhouse.dto.EmpleadoResponseDTO;
import com.coderhouse.service.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller REST para gestionar operaciones sobre la entidad Empleado.
 * Expone endpoints para operaciones CRUD y gestión de relaciones con Proyectos.
 */
@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "API para gestión de empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    /**
     * Obtiene todos los empleados.
     * 
     * @return Lista de todos los empleados
     */
    @Operation(summary = "Obtener todos los empleados", description = "Retorna una lista con todos los empleados registrados")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> obtenerTodos() {
        List<EmpleadoResponseDTO> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }
    
    /**
     * Obtiene un empleado por su ID.
     * 
     * @param id ID del empleado
     * @return Empleado encontrado
     */
    @Operation(summary = "Obtener empleado por ID", description = "Retorna un empleado específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> obtenerPorId(@PathVariable Long id) {
        EmpleadoResponseDTO empleado = empleadoService.obtenerPorId(id);
        return ResponseEntity.ok(empleado);
    }
    
    /**
     * Obtiene un empleado por su email.
     * 
     * @param email Email del empleado
     * @return Empleado encontrado
     */
    @Operation(summary = "Obtener empleado por email", description = "Retorna un empleado específico por su email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<EmpleadoResponseDTO> obtenerPorEmail(@PathVariable String email) {
        EmpleadoResponseDTO empleado = empleadoService.obtenerPorEmail(email);
        return ResponseEntity.ok(empleado);
    }
    
    /**
     * Obtiene un empleado por su legajo.
     * 
     * @param legajo Legajo del empleado
     * @return Empleado encontrado
     */
    @Operation(summary = "Obtener empleado por legajo", description = "Retorna un empleado específico por su legajo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/legajo/{legajo}")
    public ResponseEntity<EmpleadoResponseDTO> obtenerPorLegajo(@PathVariable String legajo) {
        EmpleadoResponseDTO empleado = empleadoService.obtenerPorLegajo(legajo);
        return ResponseEntity.ok(empleado);
    }
    
    /**
     * Crea un nuevo empleado.
     * 
     * @param empleadoDTO Datos del empleado a crear
     * @return Empleado creado
     */
    @Operation(summary = "Crear un nuevo empleado", description = "Crea un nuevo empleado con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o empleado duplicado"),
        @ApiResponse(responseCode = "409", description = "Conflicto - Email o legajo ya existe")
    })
    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> crear(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoResponseDTO empleadoCreado = empleadoService.crear(empleadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }
    
    /**
     * Actualiza un empleado existente.
     * 
     * @param id ID del empleado a actualizar
     * @param empleadoDTO Datos actualizados del empleado
     * @return Empleado actualizado
     */
    @Operation(summary = "Actualizar un empleado", description = "Actualiza los datos de un empleado existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto - Email o legajo ya existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoResponseDTO empleadoActualizado = empleadoService.actualizar(id, empleadoDTO);
        return ResponseEntity.ok(empleadoActualizado);
    }
    
    /**
     * Elimina un empleado por su ID.
     * 
     * @param id ID del empleado a eliminar
     * @return 204 No Content
     */
    @Operation(summary = "Eliminar un empleado", description = "Elimina un empleado por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Asigna un proyecto a un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado
     */
    @Operation(summary = "Asignar proyecto a empleado", description = "Asigna un proyecto existente a un empleado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto asignado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Empleado o proyecto no encontrado")
    })
    @PostMapping("/{empleadoId}/proyectos/{proyectoId}")
    public ResponseEntity<EmpleadoResponseDTO> asignarProyecto(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        EmpleadoResponseDTO empleado = empleadoService.asignarProyecto(empleadoId, proyectoId);
        return ResponseEntity.ok(empleado);
    }
    
    /**
     * Remueve un proyecto de un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado
     */
    @Operation(summary = "Remover proyecto de empleado", description = "Remueve la asignación de un proyecto de un empleado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto removido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Empleado o proyecto no encontrado")
    })
    @DeleteMapping("/{empleadoId}/proyectos/{proyectoId}")
    public ResponseEntity<EmpleadoResponseDTO> removerProyecto(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        EmpleadoResponseDTO empleado = empleadoService.removerProyecto(empleadoId, proyectoId);
        return ResponseEntity.ok(empleado);
    }
}

