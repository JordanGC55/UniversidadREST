package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository carreraRepository;

    //SE ejecuta antes de cada test
    @BeforeEach
    void setup(){
        //Given
        carreraRepository.save(DatosDummy.carreraD1());
        carreraRepository.save(DatosDummy.carreraD2());
        carreraRepository.save(DatosDummy.carreraD3());
    }

    @AfterEach
    void tearDown()
    {
        carreraRepository.deleteAll();
    }

    @Test
    @DisplayName("Test: Busca carreras por nombre")
    void findCarrerasByNombreContains(){
        //Given
        /*carreraRepository.save(DatosDummy.carreraD1());
        carreraRepository.save(DatosDummy.carreraD2());
        carreraRepository.save(DatosDummy.carreraD3());

         */

        //When
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("Sistemas");

        //Then
        assertThat(((List<Carrera>)expected).size() == 2).isTrue();

    }


    @Test
    @DisplayName("Test: Buscar carreras por nombre No case sensitive")
    void findCarrerasByNombreContainsIgnoreCase(){
        //Given
        /*carreraRepository.save(DatosDummy.carreraD1());
        carreraRepository.save(DatosDummy.carreraD2());
        carreraRepository.save(DatosDummy.carreraD3());

         */

        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("sistemas");

        //Then
        assertThat(expected.size() == 2).isTrue();


    }
    @Test
    @DisplayName("Test: Bucar carreras mayores a 4 años")
    void findCarrerasByCantidadAniosAfter(){
        //Given
        /*carreraRepository.save(DatosDummy.carreraD1());
        carreraRepository.save(DatosDummy.carreraD2());
        carreraRepository.save(DatosDummy.carreraD3());

         */

        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(4);

        //Then
        assertThat(expected.size() == 2).isTrue();

    }
}
