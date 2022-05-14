package com.ibm.academia.apirest.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class CarreraDTO implements Serializable {

    private Integer carreraId;
    private String nombre;
    private Integer cantidadMaterias;
    private Integer cantidadAnios;
    //private Date fechaCreacion;



    private static final long serialVersionUID = 3196381622089728717L;
}
