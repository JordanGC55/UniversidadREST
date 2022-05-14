package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import static com.ibm.academia.apirest.datos.DatosDummy.carreraD1;
import static com.ibm.academia.apirest.datos.DatosDummy.carreraD3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarreraDAOImplementTest {

    CarreraDAO carreraDao;
    CarreraRepository carreraRepository;

    @BeforeEach
    void setUp()
    {
        carreraRepository = mock(CarreraRepository.class);
        carreraDao = new CarreraDAOImplements(carreraRepository);
    }

    @Test
    @DisplayName("Test: Buscar carreras por nombre")
    void findCarrerasByNombreContains()
    {
        //Given
        String nombreCarrera = "Ingenieria";

        when(carreraRepository.findCarrerasByNombreContains(nombreCarrera))
                .thenReturn(Arrays.asList(carreraD1(), carreraD3()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombreCarrera);

        //Then
        assertThat(expected.get(0)).isEqualTo(carreraD1());
        assertThat(expected.get(1)).isEqualTo(carreraD3());

        verify(carreraRepository).findCarrerasByNombreContains(nombreCarrera);

    }

    @Test
    @DisplayName("Test: Buscar carreras por nombre NO case sensitive")
    void findCarrerasByNombreContainsIgnoreCase()
    {
        //Given
        String nombre = "ingenieria";
        when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre))
                .thenReturn(Arrays.asList(DatosDummy.carreraD1(), DatosDummy.carreraD3()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombre);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.carreraD1());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.carreraD3());

        verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
    }

    @Test
    @DisplayName("Test: Buscar carreras mayores a N años")
    void findCarrerasByCantidadAniosAfter()
    {
        //Given
        Integer cantidad = 4;
        when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad))
                .thenReturn(Arrays.asList(DatosDummy.carreraD1(), DatosDummy.carreraD3()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidad);

        //Then
        assertThat(expected.get(0)).isEqualTo(DatosDummy.carreraD1());
        assertThat(expected.get(1)).isEqualTo(DatosDummy.carreraD3());

        verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);
    }

}
