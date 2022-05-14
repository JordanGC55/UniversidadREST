package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

    @Query(value = "select * from universidad.pabellones where localidad = ?1", nativeQuery = true)
    public Iterable<Pabellon> buscarPabellonLocalidad(String localidad);

    //Demas Opciones
    //@Query("select p Pabellon p join fetch p.direccion d where d.localidad = ?1")
    //public Iterable<Pabellon> buscarPabellonLocalidad(String localidad);
    //public Iterable<Pabellon>findPabellonesByLocalidad(String direccion);

    public Iterable<Pabellon> findPabellonesByNombre(String nombre);
}
