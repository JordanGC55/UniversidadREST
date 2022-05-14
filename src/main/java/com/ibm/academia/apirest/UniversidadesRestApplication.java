package com.ibm.academia.apirest;

import com.ibm.academia.apirest.services.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class UniversidadesRestApplication {
	//@Autowired
	//private AlumnoDAO alumnoDao;

	public static void main(String[] args) {
		SpringApplication.run(UniversidadesRestApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner runner(){
		return args -> {
			/*Direccion direccion = new Direccion("calle 12", "4", "555", null, null, "Camping");
			Persona alumno = new Alumno(null, "Maria", "Flores", "18310192", direccion);
			Persona personaGuardar =  alumnoDao.guardar(alumno);
			System.out.println(personaGuardar.toString());*/


			/*List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
			alumnos.forEach(System.out::println);*/
		//};
	//}

}
