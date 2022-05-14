package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository{

    @Query("select a from Alumno a join fetch a.carrera c where c.nombre = ?1")
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);


}
