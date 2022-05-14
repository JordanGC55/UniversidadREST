package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProfesorDAOImplements extends PersonaDAOImplements implements ProfesorDAO{

    @Autowired
    public ProfesorDAOImplements(@Qualifier("repositorioProfesores") PersonaRepository repository) {
        super(repository);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findProfesoresByCarrera(String carrera) {
        return ((ProfesorRepository)repository).findProfesoresByCarrera(carrera);
    }

    @Override
    @Transactional
    public Persona actualizar(Persona profeEncontrada, Persona profesor) {
        Persona profeAct = null;
        profeEncontrada.setNombre(profesor.getNombre());
        profeEncontrada.setApellido(profesor.getApellido());
        profeEncontrada.setDireccion(profesor.getDireccion());
        profeAct = repository.save(profeEncontrada);
        return profeAct;
    }
}
