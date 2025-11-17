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

import com.coderhouse.models.Proyecto;
import com.coderhouse.service.ProyectoService;

/**
 * Controller REST para gestionar operaciones sobre la entidad Proyecto.
 * Expone endpoints para operaciones CRUD y gestión de relaciones con Empleados.
 */
@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoService proyectoService;
    
    /**
     * Obtiene todos los proyectos.
     * 
     * @return Lista de todos los proyectos
     */
    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerTodos() {
        List<Proyecto> proyectos = proyectoService.obtenerTodos();
        return ResponseEntity.ok(proyectos);
    }
    
    /**
     * Obtiene un proyecto por su ID.
     * 
     * @param id ID del proyecto
     * @return Proyecto encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerPorId(@PathVariable Long id) {
        return proyectoService.obtenerPorId(id)
            .map(proyecto -> ResponseEntity.ok(proyecto))
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca proyectos por nombre (búsqueda parcial, case-insensitive).
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de proyectos encontrados
     */
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Proyecto>> buscarPorNombre(@PathVariable String nombre) {
        List<Proyecto> proyectos = proyectoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(proyectos);
    }
    
    /**
     * Crea un nuevo proyecto.
     * 
     * @param proyecto Datos del proyecto a crear
     * @return Proyecto creado
     */
    @PostMapping
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto proyecto) {
        Proyecto proyectoCreado = proyectoService.crear(proyecto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoCreado);
    }
    
    /**
     * Actualiza un proyecto existente.
     * 
     * @param id ID del proyecto a actualizar
     * @param proyecto Datos actualizados del proyecto
     * @return Proyecto actualizado o 400 si hay errores
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        try {
            Proyecto proyectoActualizado = proyectoService.actualizar(id, proyecto);
            return ResponseEntity.ok(proyectoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Elimina un proyecto por su ID.
     * 
     * @param id ID del proyecto a eliminar
     * @return 204 si se eliminó correctamente o 400 si hay errores
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            proyectoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Asigna un empleado a un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado o 400 si hay errores
     */
    @PostMapping("/{proyectoId}/empleados/{empleadoId}")
    public ResponseEntity<?> asignarEmpleado(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        try {
            Proyecto proyecto = proyectoService.asignarEmpleado(proyectoId, empleadoId);
            return ResponseEntity.ok(proyecto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Remueve un empleado de un proyecto.
     * 
     * @param proyectoId ID del proyecto
     * @param empleadoId ID del empleado
     * @return Proyecto actualizado o 400 si hay errores
     */
    @DeleteMapping("/{proyectoId}/empleados/{empleadoId}")
    public ResponseEntity<?> removerEmpleado(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        try {
            Proyecto proyecto = proyectoService.removerEmpleado(proyectoId, empleadoId);
            return ResponseEntity.ok(proyecto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

