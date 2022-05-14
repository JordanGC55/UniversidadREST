package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer> {

    public Iterable<Aula> findAulasByPizarron(Pizarron tipoPizarron);

    @Query("select a from Aula a join fetch a.pabellon p where p.nombre = ?1")
    public Iterable<Aula> findAulasByPabellon_Nombre(String pabellon_nombre);

    public Iterable<Aula> findAulasByNumeroAula(Integer numeroAula);

}
