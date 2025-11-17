package com.coderhouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.models.Proyecto;

/**
 * Repositorio para la entidad Proyecto.
 * Proporciona métodos CRUD y consultas personalizadas.
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    
    /**
     * Busca un proyecto por su nombre.
     * 
     * @param nombre Nombre del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyecto> findByNombre(String nombre);
    
    /**
     * Busca todos los proyectos que contengan el nombre dado.
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de proyectos encontrados
     */
    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Verifica si existe un proyecto con el nombre dado.
     * 
     * @param nombre Nombre a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByNombre(String nombre);
}

