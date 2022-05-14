package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;


public interface ProfesorDAO extends PersonaDAO{

    public Iterable<Persona> findProfesoresByCarrera(String carrera);
    //Iterable<Persona> buscarProfesorCarrera(String carrera);
    public Persona actualizar(Persona profeEncontrada, Persona profesor);
}
