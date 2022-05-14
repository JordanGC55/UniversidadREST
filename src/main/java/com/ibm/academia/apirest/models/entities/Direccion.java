package com.ibm.academia.apirest.models.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

//Librerias lombook
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Esto no es una entidad
@Embeddable
public class Direccion implements Serializable {
    private String calle;
    private String numero;
    private String codigo_postal;
    private String departamento;
    private String piso;
    private String localidad;

    private static final long serialVersionUID = 8500313989796186076L;
}
