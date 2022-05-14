package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOImplements extends PersonaDAOImplements implements EmpleadoDAO{

    public EmpleadoDAOImplements(@Qualifier("repositorioEmpleados")PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    public Persona actualizar(Persona empleadoEncontrada, Persona empleado) {
        Persona empleadoAct = null;
        empleadoEncontrada.setNombre(empleado.getNombre());
        empleadoEncontrada.setApellido(empleado.getApellido());
        empleadoEncontrada.setDireccion(empleado.getDireccion());
        empleadoAct = repository.save(empleadoEncontrada);
        return empleadoAct;
    }
}
