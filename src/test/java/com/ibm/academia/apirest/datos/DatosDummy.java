package com.ibm.academia.apirest.datos;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.*;

import java.math.BigDecimal;

public class DatosDummy {

    public static Carrera carreraD1(){
        return new Carrera(null, "Ingenieria en Sistemas", 50, 5);
    }
    public static Carrera carreraD2(){
        return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
    }
    public static Carrera carreraD3(){
        return new Carrera(null, "Ingenieria Industrial", 60, 5);
    }
    public static Persona empleado01()
    {
        return new Empleado(null, "Lautaro", "Lopez", "25147036", new Direccion(), new BigDecimal("46750"), TipoEmpleado.ADMINISTRATIVO);
    }

    public static Persona empleado02()
    {
        return new Empleado(null, "Lenadro", "Lopez", "25174630", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
    }

    public static Persona profesor01()
    {
        return new Profesor(null, "Martin", "Torres", "3377899", new Direccion(), new BigDecimal("600000"));
    }

    public static Persona alumno01()
    {
        return new Alumno(null, "Jhon", "Benitez", "4223715", new Direccion());
    }

    public static Persona alumno02()
    {
        return new Alumno(null, "Andres", "Benitez", "45233891", new Direccion());
    }

    public static Persona alumno03()
    {
        return new Alumno(null, "Joaquin", "Leon", "45233012", new Direccion());
    }

}
