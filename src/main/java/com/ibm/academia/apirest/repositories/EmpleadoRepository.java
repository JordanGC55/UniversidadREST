package com.ibm.academia.apirest.repositories;


import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{

    @Query("select e from Empleado e where e.tipoEmpleado = ?1")
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);


}
