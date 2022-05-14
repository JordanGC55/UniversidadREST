package com.ibm.academia.apirest.services;


import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarreraDAOImplements extends GenericoDAOImplements<Carrera, CarreraRepository> implements CarreraDAO{


    @Autowired
    public CarreraDAOImplements(CarreraRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByNombreContains(String nombre)
    {
        return repository.findCarrerasByNombreContains(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre)
    {

        return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios)
    {
        return repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
    }

    @Override
    @Transactional
    public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera) {
        Carrera carreraActualizar = null;
        carreraEncontrada.setCantidadAnios(carrera.getCantidadAnios());
        carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
        carreraActualizar = repository.save(carreraEncontrada);
        return carreraActualizar;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
        return repository.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
    }

    /*@Autowired
    private CarreraRepository carreraRepository;*/

    /*@Override
    @Transactional(readOnly = true)
    public Optional<Carrera> buscarPorId(Integer id) {
        return carreraRepository.findById(id);
    }

    @Override
    @Transactional
    public Carrera guardar(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> buscarTodos() {
        return carreraRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        carreraRepository.deleteById(id);
    }

     */
}
