package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;

public interface AulaDAO extends GenericoDAO<Aula>{

    public Iterable<Aula> findAulasByPizarron(Pizarron tipoPizarron);

    public Iterable<Aula> findAulasByPabellon_Nombre(String pabellon_nombre);

    public Iterable<Aula> findAulasByNumeroAula(Integer numeroAula);

    public Aula actualizar(Aula aulaEncontrada, Aula aula);
}
