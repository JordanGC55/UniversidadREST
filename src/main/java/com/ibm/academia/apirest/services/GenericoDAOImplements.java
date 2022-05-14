package com.ibm.academia.apirest.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//Se hace lo mismo pero yo ocupo decirle al generico cual es mi objeto y mi repositorio
public class GenericoDAOImplements <E, R extends CrudRepository<E, Integer>> implements GenericoDAO<E> {

    protected final R repository;

    public GenericoDAOImplements(R repository)
    {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        repository.deleteById(id);

    }
}
