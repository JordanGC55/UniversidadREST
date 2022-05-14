package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AulaDAOImplements extends GenericoDAOImplements<Aula, AulaRepository>  implements AulaDAO{

    @Autowired
    public AulaDAOImplements(AulaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByPizarron(Pizarron tipoPizarron) {
        return repository.findAulasByPizarron(tipoPizarron);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByPabellon_Nombre(String pabellon_nombre) {
        return repository.findAulasByPabellon_Nombre(pabellon_nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> findAulasByNumeroAula(Integer numeroAula) {
        return repository.findAulasByNumeroAula(numeroAula);
    }

    @Override
    public Aula actualizar(Aula aulaEncontrada, Aula aula) {
        Aula aulaActualizar = null;
        aulaEncontrada.setNumeroAula(aula.getNumeroAula());
        aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());
        aulaEncontrada.setMedidas(aula.getMedidas());
        aulaActualizar = repository.save(aulaEncontrada);
        return aulaActualizar;
    }


}
