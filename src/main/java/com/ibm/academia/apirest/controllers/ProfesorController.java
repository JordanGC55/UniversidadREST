package com.ibm.academia.apirest.controllers;


import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
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
@RequestMapping("/profesor")
public class ProfesorController {


    @Autowired
    @Qualifier("profesorDAOImplements")
    private PersonaDAO profesorDao;

    /**
     * Endpoint para consultar todos los profesores
     * @return Retorna una lista de profesores.
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/profesores/lista")
    public ResponseEntity<?> listarTodas()
    {
        List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();

        return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
    }

    /**
     * Endpoint para consultar un profesor por id
     * @param profesorId Parámetro de búsqueda del profesor
     * @return Retorna un objeto de tipo profesor
     * @NotFoundException En caso de que falle buscando el profesor
     * @author Jordan - 14-05-2022
     */

    @GetMapping("/profesorId/{profesorId}")
    public ResponseEntity<?> obtenerAlumnoId(@PathVariable Integer profesorId ){

        Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
        if(!oProfesor.isPresent()){
            throw new NotFoundException(String.format("Profesor con Id: %d no encontrado", profesorId));

        }

        return new ResponseEntity<Persona>(oProfesor.get(),HttpStatus.OK);
    }


    /***
     * Endpoint para insertar un profesor
     * @param profesor recibe datos de profesor
     * @return profesor insertaso
     */
    @PostMapping("/insert")
    public ResponseEntity<?> crearProfesor(@RequestBody Persona profesor){

        Persona profesorGuardado = profesorDao.guardar(profesor);
        return new ResponseEntity<Persona>(profesor,HttpStatus.CREATED);

    }


    /**
     * Actualizar un profesor
     * @param profesorId Parametro para buscar la pabellon
     * @param profesor objeto (json con la informacion a modificar)
     * @NotFoundException En caso de que falle actualizando el objeto de profesor
     * @return Retorna un objeto de tipo profesor con la info actualizada
     * @Author Jordan Gonzalez
     */
    @PutMapping("/update/profesorId/{profesorId}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Persona profesor) {
        Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
        if (!oProfesor.isPresent()) {
            throw new NotFoundException(String.format("El profesor con Id: %d no existe", profesorId));
        }

        Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(oProfesor.get(), profesor);
        return new ResponseEntity<Persona>(profesorActualizado,HttpStatus.OK);
    }

    /***
     * Endpoint para eliminar profesor
     * @param profesorId recibe id de profesor
     * @return respuesta de profesor eliminado
     * @author Jordan
     */
    @DeleteMapping("/profesorId/{profesorId}")
    public ResponseEntity<?> eliminarProfesor(@PathVariable Integer profesorId){

        Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);

        if(!oProfesor.isPresent()){
            throw new NotFoundException(String.format("El Profesor con Id: %d no existe", profesorId));
        }
        profesorDao.eliminarPorId(oProfesor.get().getId());
        return new ResponseEntity<String>("Profesor ID: " + profesorId+ " se elimino satisfactoriamente", HttpStatus.OK);
    }


}
