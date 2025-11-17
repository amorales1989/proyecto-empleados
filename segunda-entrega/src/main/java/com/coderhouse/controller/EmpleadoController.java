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

import com.coderhouse.models.Empleado;
import com.coderhouse.service.EmpleadoService;

/**
 * Controller REST para gestionar operaciones sobre la entidad Empleado.
 * Expone endpoints para operaciones CRUD y gestión de relaciones con Proyectos.
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    /**
     * Obtiene todos los empleados.
     * 
     * @return Lista de todos los empleados
     */
    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodos() {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }
    
    /**
     * Obtiene un empleado por su ID.
     * 
     * @param id ID del empleado
     * @return Empleado encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id)
            .map(empleado -> ResponseEntity.ok(empleado))
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Obtiene un empleado por su email.
     * 
     * @param email Email del empleado
     * @return Empleado encontrado o 404 si no existe
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Empleado> obtenerPorEmail(@PathVariable String email) {
        return empleadoService.obtenerPorEmail(email)
            .map(empleado -> ResponseEntity.ok(empleado))
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Obtiene un empleado por su legajo.
     * 
     * @param legajo Legajo del empleado
     * @return Empleado encontrado o 404 si no existe
     */
    @GetMapping("/legajo/{legajo}")
    public ResponseEntity<Empleado> obtenerPorLegajo(@PathVariable String legajo) {
        return empleadoService.obtenerPorLegajo(legajo)
            .map(empleado -> ResponseEntity.ok(empleado))
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo empleado.
     * 
     * @param empleado Datos del empleado a crear
     * @return Empleado creado o 400 si hay errores de validación
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoCreado = empleadoService.crear(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Actualiza un empleado existente.
     * 
     * @param id ID del empleado a actualizar
     * @param empleado Datos actualizados del empleado
     * @return Empleado actualizado o 400/404 si hay errores
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = empleadoService.actualizar(id, empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Elimina un empleado por su ID.
     * 
     * @param id ID del empleado a eliminar
     * @return 204 si se eliminó correctamente o 400 si hay errores
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            empleadoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Asigna un proyecto a un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado o 400 si hay errores
     */
    @PostMapping("/{empleadoId}/proyectos/{proyectoId}")
    public ResponseEntity<?> asignarProyecto(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        try {
            Empleado empleado = empleadoService.asignarProyecto(empleadoId, proyectoId);
            return ResponseEntity.ok(empleado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Remueve un proyecto de un empleado.
     * 
     * @param empleadoId ID del empleado
     * @param proyectoId ID del proyecto
     * @return Empleado actualizado o 400 si hay errores
     */
    @DeleteMapping("/{empleadoId}/proyectos/{proyectoId}")
    public ResponseEntity<?> removerProyecto(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        try {
            Empleado empleado = empleadoService.removerProyecto(empleadoId, proyectoId);
            return ResponseEntity.ok(empleado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

