package com.ibm.academia.apirest.controllers;


import com.ibm.academia.apirest.exeptions.BadRequestExeption;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.PabellonDAO;
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
@RequestMapping("/pabellon")
public class PabellonController {

    @Autowired
    private PabellonDAO pabellonDAO;

    /**
     * Endpoint para consultar todos los pabellones
     * @return Retorna una lista de pabellones.
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/lista/pabellones")
    public List<Pabellon> buscarTodos(){

        List <Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();

        if(pabellones.isEmpty()){
            throw new BadRequestExeption("No existen carreras");
        }
        return pabellones;

    }


    /**
     * Endpoint para consultar un pabellon por id
     * @param pabellonId Parámetro de búsqueda del pabellon
     * @return Retorna un objeto de tipo pabellon
     * @NotFoundException En caso de que falle buscando el pabellon
     * @author Jordan - 14-05-2022
     */
    @GetMapping("/id/{pabellonId}")
    public Pabellon buscarCarreraPorId(@PathVariable Integer pabellonId){
        Pabellon pabellon = pabellonDAO.buscarPorId(pabellonId).orElse(null);
        if(pabellon == null){
            throw new BadRequestExeption(String.format("El pabellon con Id: &d no existe", pabellonId));

        }
        return pabellon;
    }

    /***
     * Endpoint para insertar un pabellon
     * @param pabellon recibe datos de pabellon
     * @return pabellon insertado
     * @author Jordan
     */
    @PostMapping("/insert")
    public ResponseEntity<?> crearPabellon(@RequestBody Pabellon pabellon){

        Pabellon pabellonGuadado = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuadado,HttpStatus.CREATED);

    }

    /**
     * Actualizar una pabellon
     * @param pabellonId Parametro para buscar la pabellon
     * @param pabellon objeto (json con la informacion a modificar)
     * @NotFoundException En caso de que falle actualizando el objeto de pabellon
     * @return Retorna un objeto de tipo pabellon con la info actualizada
     * @Author Jordan Gonzalez
     */
    @PutMapping("/update/pabellonId/{pabellonId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon){
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException(String.format("El pabellon con Id: %d no existe", pabellonId));
        }
        Pabellon pabellonActulizar = pabellonDAO.actualizar(oPabellon.get(), pabellon);
        return new ResponseEntity<Pabellon>(pabellonActulizar, HttpStatus.OK);
    }

    /***
     * Endpoint para eliminar pabellon
     * @param pabellonId recibe id de pabellon
     * @return respuesta de pabellon eliminado
     * @author Jordan
     */

    @DeleteMapping("/pabellonId/{pabellonId}")
    public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException(String.format("El pabellon con Id: %d no existe", pabellonId));
        }
        pabellonDAO.eliminarPorId(pabellonId);
        respuesta.put("Ok", "Pabellon Id: " + pabellonId + " Eliminado exitosamente");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);


    }
}
