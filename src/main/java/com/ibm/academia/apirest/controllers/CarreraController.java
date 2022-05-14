package com.ibm.academia.apirest.controllers;


import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.exeptions.BadRequestExeption;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.models.mapper.CarreraMapper;
import com.ibm.academia.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraDAO carreraDAO;

    /**
     * Endpoint para consultar todaos los carreras
     * @return Retorna una lista de carreras.
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/lista/carreras")
    public List<Carrera> buscarTodos(){

        List <Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();

        if(carreras.isEmpty()){
            throw new BadRequestExeption("No existen carreras");
        }
        return carreras;

    }

    /**
     * Endpoint para consultar una carrera por id
     * @param carreraId Parámetro de búsqueda de la carrera
     * @return Retorna un objeto de tipo carrera
     * @NotFoundException En caso de que falle buscando la carrera
     * @author Jordan - 14-05-2022
     */

    @GetMapping("/id/{carreraId}")
    public Carrera buscarCarreraPorId(@PathVariable Integer carreraId){

        /*Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new BadRequestExeption(String.format("La carrera con id: %d no existe", carreraId));
        }
        return oCarrera.get();

         */

        //Otra forma

        //Buscar una carrrera
        Carrera carrera = carreraDAO.buscarPorId(carreraId).orElse(null);
        if(carrera == null){
            throw new BadRequestExeption(String.format("La carrera con Id: &d no existe", carreraId));

        }
        return carrera;
    }

    /***
     * Endpoint para insertar una carrera
     * @param carrera recibe datos de carrera
     * @return carrera insertado
     * @author Jordan
     */

    @PostMapping
    //Esto recibe un json, entonces lo debo de especificar
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result){
        //Aquie no va esta logica
        /*
        if(carrera.getCantidadAnios() < 0){
            throw new BadRequestExeption("El campo cantidadAnios tiene que ser mayor a 0");
        }
        if(carrera.getCantidadMaterias()<0){
            throw new BadRequestExeption("El campo cantidadMaterias tiene que ser mayor a 0");
        }
         */

        //Insertar una carrera
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors()){
            List<String> listaErrorres = result.getFieldErrors()
                    .stream()
                    .map( errores -> "Campo: " + errores.getField() + " " +errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista de Errores", listaErrorres);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);

        }
        Carrera carreraGuardada = carreraDAO.guardar(carrera);

        return  new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
    }

    /**
     * Actualizar una carrera
     * @param carreraId Parametro para buscar la carrera
     * @param carrera objeto (json con la informacion a modificar)
     * @NotFoundException En caso de que falle actualizando el objeto de carrera
     * @return Retorna un objeto de tipo carrera con la info actualizada
     * @Author Jordan Gonzalez
     */


    @PutMapping("/update/carreraId/{carreraId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera){
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
        }
        Carrera carreraActulizar = carreraDAO.actualizar(oCarrera.get(), carrera);
        return new ResponseEntity<Carrera>(carreraActulizar, HttpStatus.OK);
    }

    /***
     * Endpoint para eliminar carrera
     * @param carreraId recibe id de carrera
     * @return respuesta de carrera eliminado
     * @author Jordan
     */
    @DeleteMapping("/carreraId/{carreraId}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
        }
        carreraDAO.eliminarPorId(carreraId);
        respuesta.put("Ok", "Carrera Id: " + carreraId + " Eliminada exitosamente");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);


    }

    /**
     * Endpoint para consultar todas las carreras mediante un DTO
     * @return Retorna un objeto de tipo carreraDTO
     * @NotFoundException En caso de que no existan valores en la BD
     * @author Jordan Gonzalez
     */

    @GetMapping("/lista/dto")
    public ResponseEntity<?> consultarTodasCarreras()
    {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();

        if(carreras.isEmpty())
            throw new NotFoundException("No existen carreras en la BD.");

        List<CarreraDTO> listaCarreras = carreras
                .stream()
                .map(CarreraMapper::mapCarrera)
                .collect(Collectors.toList());
        return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
    }




}
