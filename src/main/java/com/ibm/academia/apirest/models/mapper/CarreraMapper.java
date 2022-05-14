package com.ibm.academia.apirest.models.mapper;

import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;

public class CarreraMapper {

    public static CarreraDTO mapCarrera(Carrera carrera){

        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setCarreraId(carrera.getId());
        carreraDTO.setNombre(carrera.getNombre());
        carreraDTO.setCantidadAnios(carrera.getCantidadAnios());
        carreraDTO.setCantidadMaterias(carrera.getCantidadMaterias());
        //carreraDTO.setFechaCreacion(carrera.getFechaCreacion());
        return carreraDTO;
    }
}
