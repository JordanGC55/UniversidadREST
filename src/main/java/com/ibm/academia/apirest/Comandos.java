package com.ibm.academia.apirest;

import com.ibm.academia.apirest.models.entities.*;
import com.ibm.academia.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.ibm.academia.apirest.enums.Pizarron.PIZARRA_BLANCA;
import static com.ibm.academia.apirest.enums.Pizarron.PIZARRA_TIZA;
import static com.ibm.academia.apirest.enums.TipoEmpleado.ADMINISTRATIVO;

@Component
public class Comandos implements CommandLineRunner {

    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private AulaDAO aulaDAO;
    @Autowired
    @Qualifier("profesorDAOImplements")
    private PersonaDAO personaDAO;
    @Autowired
    private PabellonDAO pabellonDAO;
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private ProfesorDAO profesorDAO;


    @Override
    public void run(String... args) throws Exception {

        //Iterable<Persona> profesoresCarrera = ((ProfesorDAO)personaDAO).findProfesoresByCarrera("Ingenieria en Sistemas");
        //profesoresCarrera.forEach(System.out::println);

        //-----Profeores------
        Iterable<Persona> profesoresCarrera = profesorDAO.findProfesoresByCarrera("Ingenieria Industrial");
        profesoresCarrera.forEach(System.out::println);


        //-------Empleado-------
        Iterable<Persona> empleadoTipo = empleadoDAO.findEmpleadoByTipoEmpleado(ADMINISTRATIVO);
        empleadoTipo.forEach(System.out::println);

        //-------Carrera-------
        Iterable<Carrera> carreraNomApe = carreraDAO.buscarCarrerasPorProfesorNombreYApellido("Jasiel", "Salvador");
        carreraNomApe.forEach(System.out::println);

        //--------Aulas-------
        //Iterable<Aula> aulaPizarron = aulaDAO.findAulasByPizarron(PIZARRA_BLANCA);
        //aulaPizarron.forEach(System.out::println);

        //Iterable<Aula> aulaPabellon = aulaDAO.findAulasByPabellon_Nombre("Delta 1");
        //aulaPabellon.forEach(System.out::println);

        //Iterable<Aula> aulaNum = aulaDAO.findAulasByNumeroAula(56);
        //aulaNum.forEach(System.out::println);

        //--------Pabellon-------

        //Iterable<Pabellon> pabellonLocalidad = pabellonDAO.buscarPabellonLocalidad("Guadalajara");
        //pabellonLocalidad.forEach(System.out::println);

        //Iterable<Pabellon> pabellonNombre = pabellonDAO.findPabellonesByNombre("Focus");
        //pabellonNombre.forEach(System.out::println);

    }
}
