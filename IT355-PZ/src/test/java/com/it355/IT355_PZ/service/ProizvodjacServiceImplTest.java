package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Proizvodjac;
import com.it355.IT355_PZ.repository.ProizvodjacRepository;
import com.it355.IT355_PZ.service.impl.ProizvodjacServiceImpl;
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

public class ProizvodjacServiceImplTest {
    @Mock
    private ProizvodjacRepository proizvodjacRepository;

    @InjectMocks
    private ProizvodjacServiceImpl proizvodjacService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Proizvodjac> proizvodjac = Arrays.asList(new Proizvodjac(), new Proizvodjac());
        when(proizvodjacRepository.findAll()).thenReturn(proizvodjac);

        List<Proizvodjac> result = proizvodjacService.findAll();

        assertEquals(proizvodjac, result);
        verify(proizvodjacRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        Proizvodjac proizvodjac = new Proizvodjac();
        when(proizvodjacRepository.findById(id)).thenReturn(Optional.of(proizvodjac));

        Optional<Proizvodjac> result = proizvodjacService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(proizvodjac, result.get());
        verify(proizvodjacRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(proizvodjacRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Proizvodjac> result = proizvodjacService.findById(id);

        assertFalse(result.isPresent());
        verify(proizvodjacRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Proizvodjac proizvodjac = new Proizvodjac();
        when(proizvodjacRepository.save(proizvodjac)).thenReturn(proizvodjac);

        Proizvodjac result = proizvodjacService.save(proizvodjac);

        assertEquals(proizvodjac, result);
        verify(proizvodjacRepository, times(1)).save(proizvodjac);
    }

    @Test
    public void testUpdate() {
        Proizvodjac publisher = new Proizvodjac();
        when(proizvodjacRepository.save(publisher)).thenReturn(publisher);

        Proizvodjac result = proizvodjacService.update(publisher);

        assertEquals(publisher, result);
        verify(proizvodjacRepository, times(1)).save(publisher);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(proizvodjacRepository).deleteById(id);

        proizvodjacService.deleteById(id);

        verify(proizvodjacRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(proizvodjacRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> proizvodjacService.deleteById(id));
        verify(proizvodjacRepository, times(1)).deleteById(id);
    }
}
