package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exeptions.BadRequestExeption;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.AulaDAO;
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
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaDAO aulaDAO;

    /**
     * Endpoint para consultar todas los aulas
     * @return Retorna una lista de aulas.
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/lista/carreras")
    public List<Aula> buscarTodos(){

        List <Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();

        if(aulas.isEmpty()){
            throw new BadRequestExeption("No existen carreras");
        }
        return aulas;

    }

    /**
     * Endpoint para consultar una aula por id
     * @param aulaId Parámetro de búsqueda de la aula
     * @return Retorna un objeto de tipo aula
     * @NotFoundException En caso de que falle buscando el aula
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/id/{aulaId}")
    public Aula buscarCarreraPorId(@PathVariable Integer aulaId){
        Aula aula = aulaDAO.buscarPorId(aulaId).orElse(null);
        if(aula == null){
            throw new BadRequestExeption(String.format("El aula con Id: &d no existe", aulaId));

        }
        return aula;
    }

    /***
     * Endpoint para insertar un aula
     * @param aula recibe datos de aula
     * @return aula insertaso
     */
    @PostMapping("/insert")
    public ResponseEntity<?> crearAula(@RequestBody Aula aula){

        Aula aulaGuadado = aulaDAO.guardar(aula);
        return new ResponseEntity<Aula>(aulaGuadado,HttpStatus.CREATED);

    }

    /**
     * endpoint para Actualizar un aula
     * @param aulaId Parametro para buscar la alumno
     * @param aula objeto (json con la informacion a modificar)
     * @NotFoundException En caso de que falle actualizando el objeto de aula
     * @return Retorna un objeto de tipo aula con la info actualizada
     * @Author Jordan Gonzalez
     */
    @PutMapping("/update/aulaId/{aulaId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer aulaId, @RequestBody Aula aula){
        Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);
        if(!oAula.isPresent()){
            throw new NotFoundException(String.format("La Aula con Id: %d no existe", aulaId));
        }
        Aula aulaActulizar = aulaDAO.actualizar(oAula.get(), aula);
        return new ResponseEntity<Aula>(aulaActulizar, HttpStatus.OK);
    }


    /***
     * Endpoint para eliminar aula
     * @param aulaId recibe id de aula
     * @return respuesta de aula eliminado
     * @author Jordan
     */
    @DeleteMapping("/aulaId/{aulaId}")
    public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);
        if(!oAula.isPresent()){
            throw new NotFoundException(String.format("La carrera con Id: %d no existe", aulaId));
        }
        aulaDAO.eliminarPorId(aulaId);
        respuesta.put("Ok", "Aula Id: " + aulaId + " Eliminada exitosamente");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);


    }

}
