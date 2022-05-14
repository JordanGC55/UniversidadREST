package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.repositories.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class AlumnoDAOImplementTest {

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AlumnoDAO alumnoDao;

    @Test
    void buscarAlumnosPorNombreCarrera()
    {
        // When
        alumnoDao.buscarAlumnoPorNombreCarrera(anyString());

        // Then

        verify(alumnoRepository).buscarAlumnoPorNombreCarrera(anyString());
    }
}
