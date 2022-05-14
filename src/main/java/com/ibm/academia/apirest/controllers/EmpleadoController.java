package com.ibm.academia.apirest.controllers;


import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    @Qualifier("empleadoDAOImplements")
    private PersonaDAO empleadoDao;

    /**
     * Endpoint para consultar todos los empleados
     * @return Retorna una lista de empleados.
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/empleados/lista")
    public ResponseEntity<?> listarTodas()
    {
        List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();

        return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un empleado por id
     * @param empleadoId Parámetro de búsqueda del empleado
     * @return Retorna un objeto de tipo alumno
     * @NotFoundException En caso de que falle buscando el empleado
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/empleadoId/{empleadoId}")
    public ResponseEntity<?> obtenerAlumnoId(@PathVariable Integer empleadoId ){

        Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
        if(!oEmpleado.isPresent()){
            throw new NotFoundException(String.format("Empleado con Id: %d no encontrado", empleadoId));

        }

        return new ResponseEntity<Persona>(oEmpleado.get(),HttpStatus.OK);
    }

    /***
     * Endpoint para insertar una empleado
     * @param empleado recibe datos de empleado
     * @return empleado insertado
     * @author Jordan
     */
    @PostMapping("/insert")
    public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado){

        Persona empleadoGuardado = empleadoDao.guardar(empleado);
        return new ResponseEntity<Persona>(empleado,HttpStatus.CREATED);

    }

    /**
     * Actualizar una empleado
     * @param empleadoId Parametro para buscar la empleado
     * @param empleado objeto (json con la informacion a modificar)
     * @NotFoundException En caso de que falle actualizando el objeto de empleado
     * @return Retorna un objeto de tipo empleado con la info actualizada
     * @Author Jordan Gonzalez
     */
    @PutMapping("/update/empleadoId/{empleadoId}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer empleadoId, @RequestBody Persona empleado) {
        Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
        if (!oEmpleado.isPresent()) {
            throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
        }

        Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(oEmpleado.get(), empleado);
        return new ResponseEntity<Persona>(empleadoActualizado,HttpStatus.OK);
    }

    /***
     * Endpoint para eliminar empleado
     * @param empleadoId recibe id de empleado
     * @return respuesta de empleado eliminado
     * @author Jordan
     */
    @DeleteMapping("/empleadoId/{empleadoId}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId){

        Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);

        if(!oEmpleado.isPresent()){
            throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
        }
        empleadoDao.eliminarPorId(oEmpleado.get().getId());
        return new ResponseEntity<String>("Empleado ID: " + empleadoId+ " se elimino satisfactoriamente", HttpStatus.OK);
    }

}
