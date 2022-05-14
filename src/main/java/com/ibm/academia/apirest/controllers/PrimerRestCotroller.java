package com.ibm.academia.apirest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/restapi")

public class PrimerRestCotroller {

    Logger logger = LoggerFactory.getLogger(PrimerRestCotroller.class);


    @GetMapping
    public String holaMundo(){
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warning log");
        logger.error("error log");

        return "Hola Mundo!!!!";
    }
}
