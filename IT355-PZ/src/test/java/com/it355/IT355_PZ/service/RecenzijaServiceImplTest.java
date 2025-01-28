package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Recenzija;
import com.it355.IT355_PZ.repository.RecenzijaRepository;
import com.it355.IT355_PZ.service.impl.RecenzijaServiceImpl;
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

public class RecenzijaServiceImplTest {
    @Mock
    private RecenzijaRepository recenzijaRepository;

    @InjectMocks
    private RecenzijaServiceImpl recenzijaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Recenzija> recenzija = Arrays.asList(new Recenzija(), new Recenzija());
        when(recenzijaRepository.findAll()).thenReturn(recenzija);

        List<Recenzija> result = recenzijaService.findAll();

        assertEquals(recenzija, result);
        verify(recenzijaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        Recenzija recenzija = new Recenzija();
        when(recenzijaRepository.findById(id)).thenReturn(Optional.of(recenzija));

        Optional<Recenzija> result = recenzijaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(recenzija, result.get());
        verify(recenzijaRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(recenzijaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Recenzija> result = recenzijaService.findById(id);

        assertFalse(result.isPresent());
        verify(recenzijaRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Recenzija recenzija = new Recenzija();
        when(recenzijaRepository.save(recenzija)).thenReturn(recenzija);

        Recenzija result = recenzijaService.save(recenzija);

        assertEquals(recenzija, result);
        verify(recenzijaRepository, times(1)).save(recenzija);
    }

    @Test
    public void testUpdate() {
        Recenzija recenzija = new Recenzija();
        when(recenzijaRepository.save(recenzija)).thenReturn(recenzija);

        Recenzija result = recenzijaService.update(recenzija);

        assertEquals(recenzija, result);
        verify(recenzijaRepository, times(1)).save(recenzija);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(recenzijaRepository).deleteById(id);

        recenzijaService.deleteById(id);

        verify(recenzijaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(recenzijaRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> recenzijaService.deleteById(id));
        verify(recenzijaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByBookId() {
        int proizvodId = 1;
        List<Recenzija> reviews = Arrays.asList(new Recenzija(), new Recenzija());
        when(recenzijaRepository.findByProizvod_Id(proizvodId)).thenReturn(reviews);

        List<Recenzija> result = recenzijaService.findByProizvodId(proizvodId);

        assertEquals(reviews, result);
        verify(recenzijaRepository, times(1)).findByProizvod_Id(proizvodId);
    }

    @Test
    public void testFindByBookIdNotFound() {
        int proizvodId = 1;
        when(recenzijaRepository.findByProizvod_Id(proizvodId)).thenReturn(Arrays.asList());

        List<Recenzija> result = recenzijaService.findByProizvodId(proizvodId);

        assertTrue(result.isEmpty());
        verify(recenzijaRepository, times(1)).findByProizvod_Id(proizvodId);
    }
}
