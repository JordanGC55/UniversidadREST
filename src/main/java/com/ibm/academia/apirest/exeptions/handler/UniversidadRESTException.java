package com.ibm.academia.apirest.exeptions.handler;


import com.ibm.academia.apirest.exeptions.BadRequestExeption;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
//El handler hace que todo pase por aqui
@ControllerAdvice
public class UniversidadRESTException {

    @ExceptionHandler(value = BadRequestExeption.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestExeption exception){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> noExisteExcepcion(NotFoundException exception){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);



    }


}
