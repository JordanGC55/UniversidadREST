package com.ibm.academia.apirest.services;

import java.util.Optional;

public interface GenericoDAO <E> {
    /*public Optional<Carrera> buscarPorId(Integer id);
    public Carrera guardar(Carrera carrera);
    public Iterable<Carrera> buscarTodos();
    public void eliminarPorId(Integer id);*/

    public Optional<E> buscarPorId(Integer id);
    public E guardar(E entidad);
    public Iterable<E> buscarTodos();
    public void eliminarPorId(Integer id);
}
