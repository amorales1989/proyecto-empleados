package com.coderhouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.models.Empleado;

/**
 * Repositorio para la entidad Empleado.
 * Proporciona métodos CRUD y consultas personalizadas.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    /**
     * Busca un empleado por su email.
     * 
     * @param email Email del empleado
     * @return Optional con el empleado encontrado
     */
    Optional<Empleado> findByEmail(String email);
    
    /**
     * Busca un empleado por su legajo.
     * 
     * @param legajo Legajo del empleado
     * @return Optional con el empleado encontrado
     */
    Optional<Empleado> findByLegajo(String legajo);
    
    /**
     * Verifica si existe un empleado con el email dado.
     * 
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
    
    /**
     * Verifica si existe un empleado con el legajo dado.
     * 
     * @param legajo Legajo a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByLegajo(String legajo);
}

