package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Korpa;
import com.it355.IT355_PZ.repository.KorpaRepository;
import com.it355.IT355_PZ.service.impl.KorpaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class KorpaServiceImplTest {
    @Mock
    private KorpaRepository korpaRepository;

    @InjectMocks
    private KorpaServiceImpl korpaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Korpa> korpa = Arrays.asList(new Korpa(), new Korpa());
        when(korpaRepository.findAll()).thenReturn(korpa);

        List<Korpa> result = korpaService.findAll();

        assertEquals(korpa, result);
        verify(korpaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        Korpa korpa = new Korpa();
        when(korpaRepository.findById(id)).thenReturn(Optional.of(korpa));

        Optional<Korpa> result = korpaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(korpa, result.get());
        verify(korpaRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(korpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Korpa> result = korpaService.findById(id);

        assertFalse(result.isPresent());
        verify(korpaRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Korpa korpa = new Korpa();
        when(korpaRepository.save(korpa)).thenReturn(korpa);

        Korpa result = korpaService.save(korpa);

        assertEquals(korpa, result);
        verify(korpaRepository, times(1)).save(korpa);
    }

    @Test
    public void testUpdate() {
        Korpa korpa = new Korpa();
        when(korpaRepository.save(korpa)).thenReturn(korpa);

        Korpa result = korpaService.update(korpa);

        assertEquals(korpa, result);
        verify(korpaRepository, times(1)).save(korpa);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(korpaRepository).deleteById(id);

        korpaService.deleteById(id);

        verify(korpaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(korpaRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> korpaService.deleteById(id));
        verify(korpaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByUserId() {
        int userId = 1;
        Korpa korpa = new Korpa();
        when(korpaRepository.findByKorisnik_Id(userId)).thenReturn(Optional.of(korpa));

        Optional<Korpa> result = korpaService.findByKorisnik_Id(userId);

        assertTrue(result.isPresent());
        assertEquals(korpa, result.get());
        verify(korpaRepository, times(1)).findByKorisnik_Id(userId);
    }

    @Test
    public void testFindByUserIdNotFound() {
        int userId = 1;
        when(korpaRepository.findByKorisnik_Id(userId)).thenReturn(Optional.empty());

        Optional<Korpa> result = korpaService.findByKorisnik_Id(userId);

        assertFalse(result.isPresent());
        verify(korpaRepository, times(1)).findByKorisnik_Id(userId);
    }
}
