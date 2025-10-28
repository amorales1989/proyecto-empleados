package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Empleado;
import com.coderhouse.models.Proyecto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void persistirEmpleado(Empleado empleado) {
        em.persist(empleado);
    }

    @Transactional
    public void persistirProyecto(Proyecto proyecto) {
        em.persist(proyecto);
    }
}