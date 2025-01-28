package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Proizvod;
import com.it355.IT355_PZ.repository.ProizvodRepository;
import com.it355.IT355_PZ.service.impl.ProizvodServiceImpl;
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
import static org.mockito.Mockito.*;

public class ProizvodServiceImplTest {
    @Mock
    private ProizvodRepository proizvodRepository;

    @InjectMocks
    private ProizvodServiceImpl proizvodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Proizvod> proizvods = Arrays.asList(new Proizvod(), new Proizvod());
        when(proizvodRepository.findAll()).thenReturn(proizvods);

        List<Proizvod> result = proizvodService.findAll();

        assertEquals(proizvods, result);
        verify(proizvodRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        Proizvod proizvods = new Proizvod();
        when(proizvodRepository.findById(id)).thenReturn(Optional.of(proizvods));

        Optional<Proizvod> result = proizvodService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(proizvods, result.get());
        verify(proizvodRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(proizvodRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Proizvod> result = proizvodService.findById(id);

        assertFalse(result.isPresent());
        verify(proizvodRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Proizvod proizvods = new Proizvod();
        when(proizvodRepository.save(proizvods)).thenReturn(proizvods);

        Proizvod result = proizvodService.save(proizvods);

        assertEquals(proizvods, result);
        verify(proizvodRepository, times(1)).save(proizvods);
    }

    @Test
    public void testUpdate() {
        Proizvod proizvods = new Proizvod();
        when(proizvodRepository.save(proizvods)).thenReturn(proizvods);

        Proizvod result = proizvodService.update(proizvods);

        assertEquals(proizvods, result);
        verify(proizvodRepository, times(1)).save(proizvods);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(proizvodRepository).deleteById(id);

        proizvodService.deleteById(id);

        verify(proizvodRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(proizvodRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> proizvodService.deleteById(id));
        verify(proizvodRepository, times(1)).deleteById(id);
    }
}
