package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository {
    /*
    @Query(value = "select * from universidad.personas where id " +
            "= (select profesor_id from universidad.profesor_carrera where " +
            "carrera_id = (select id from universidad.carreras " +
            "where nombre = ?1))", nativeQuery = true)

     */

    @Query("select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
    public Iterable<Persona> findProfesoresByCarrera(String carrera);

    //@Query("select p from Profesor p where p.nombre =?1")
    //public Iterable<Persona> buscarProfesorCarrera(String carrera);

}
