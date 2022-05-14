package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PabellonDAOImplements extends GenericoDAOImplements<Pabellon, PabellonRepository> implements PabellonDAO{

    @Autowired
    public PabellonDAOImplements(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPabellonLocalidad(String localidad) {
        return repository.buscarPabellonLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> findPabellonesByNombre(String nombre) {
        return repository.findPabellonesByNombre(nombre);
    }

    @Override
    public Pabellon actualizar(Pabellon pabellonEncontrada, Pabellon pabellon) {
        Pabellon pabellonActualizar = null;
        pabellonEncontrada.setNombre(pabellon.getNombre());
        pabellonEncontrada.setTamanoMetrosCuadrados(pabellon.getTamanoMetrosCuadrados());
        pabellonActualizar = repository.save(pabellonEncontrada);
        return pabellonActualizar;
    }
}
