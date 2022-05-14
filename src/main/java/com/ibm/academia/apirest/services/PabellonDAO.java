package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{

    public Iterable<Pabellon> buscarPabellonLocalidad(String localidad);

    public Iterable<Pabellon> findPabellonesByNombre(String nombre);

    public Pabellon actualizar(Pabellon pabellonEncontrada, Pabellon pabellon);
}
