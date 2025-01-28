package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.KorpaStavka;
import com.it355.IT355_PZ.repository.KorpaStavkaRepository;
import com.it355.IT355_PZ.service.impl.KorpaStavkaServiceImpl;
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
import static org.mockito.Mockito.times;

public class KorpaStavkaServiceImpTest {
    @Mock
    private KorpaStavkaRepository korpaStavkaRepository;

    @InjectMocks
    private KorpaStavkaServiceImpl korpaStavkaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<KorpaStavka> korpaStavkas = Arrays.asList(new KorpaStavka(), new KorpaStavka());
        when(korpaStavkaRepository.findAll()).thenReturn(korpaStavkas);

        List<KorpaStavka> result = korpaStavkaService.findAll();

        assertEquals(korpaStavkas, result);
        verify(korpaStavkaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        KorpaStavka korpaStavkas = new KorpaStavka();
        when(korpaStavkaRepository.findById(id)).thenReturn(Optional.of(korpaStavkas));

        Optional<KorpaStavka> result = korpaStavkaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(korpaStavkas, result.get());
        verify(korpaStavkaRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(korpaStavkaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<KorpaStavka> result = korpaStavkaService.findById(id);

        assertFalse(result.isPresent());
        verify(korpaStavkaRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        KorpaStavka korpaStavkas = new KorpaStavka();
        when(korpaStavkaRepository.save(korpaStavkas)).thenReturn(korpaStavkas);

        KorpaStavka result = korpaStavkaService.save(korpaStavkas);

        assertEquals(korpaStavkas, result);
        verify(korpaStavkaRepository, times(1)).save(korpaStavkas);
    }

    @Test
    public void testUpdate() {
        KorpaStavka korpaStavkas = new KorpaStavka();
        when(korpaStavkaRepository.save(korpaStavkas)).thenReturn(korpaStavkas);

        KorpaStavka result = korpaStavkaService.update(korpaStavkas);

        assertEquals(korpaStavkas, result);
        verify(korpaStavkaRepository, times(1)).save(korpaStavkas);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(korpaStavkaRepository).deleteById(id);

        korpaStavkaService.deleteById(id);

        verify(korpaStavkaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(korpaStavkaRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> korpaStavkaService.deleteById(id));
        verify(korpaStavkaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByCartUserId() {
        int userId = 1;
        List<KorpaStavka> korpaStavkas = Arrays.asList(new KorpaStavka(), new KorpaStavka());
        when(korpaStavkaRepository.findByKorpa_Korisnik_Id(userId)).thenReturn(korpaStavkas);

        List<KorpaStavka> result = korpaStavkaService.findByKorpa_Korisnik_Id(userId);

        assertEquals(korpaStavkas, result);
        verify(korpaStavkaRepository, times(1)).findByKorpa_Korisnik_Id(userId);
    }

    @Test
    public void testFindByCartUserIdNotFound() {
        int userId = 1;
        when(korpaStavkaRepository.findByKorpa_Korisnik_Id(userId)).thenReturn(Arrays.asList());

        List<KorpaStavka> result = korpaStavkaService.findByKorpa_Korisnik_Id(userId);

        assertTrue(result.isEmpty());
        verify(korpaStavkaRepository, times(1)).findByKorpa_Korisnik_Id(userId);
    }

    @Test
    public void testDeleteByCartUserId() {
        int userId = 1;

        doNothing().when(korpaStavkaRepository).deleteByKorpa_Korisnik_Id(userId);

        korpaStavkaService.deleteByKorpa_Korisnik_Id(userId);

        verify(korpaStavkaRepository, times(1)).deleteByKorpa_Korisnik_Id(userId);
    }
}
