package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Korisnik;
import com.it355.IT355_PZ.repository.KorisnikRepository;
import com.it355.IT355_PZ.service.impl.KorisnikServiceImpl;
import com.it355.IT355_PZ.tool.JwtTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class KorisnikServiceImplTest {
    @Mock
    private KorisnikRepository korisnikRepository;

    @InjectMocks
    private KorisnikServiceImpl korisnikService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Korisnik> korisnik = Arrays.asList(new Korisnik(), new Korisnik());
        when(korisnikRepository.findAll()).thenReturn(korisnik);

        List<Korisnik> result = korisnikService.findAll();

        assertEquals(korisnik, result);
        verify(korisnikRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        int id = 1;
        Korisnik korisnik = new Korisnik();
        when(korisnikRepository.findById(id)).thenReturn(Optional.of(korisnik));

        Optional<Korisnik> result = korisnikService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(korisnik, result.get());
        verify(korisnikRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        int id = 1;
        when(korisnikRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Korisnik> result = korisnikService.findById(id);

        assertFalse(result.isPresent());
        verify(korisnikRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Korisnik korisnik = new Korisnik();
        when(korisnikRepository.save(korisnik)).thenReturn(korisnik);

        Korisnik result = korisnikService.save(korisnik);

        assertEquals(korisnik, result);
        verify(korisnikRepository, times(1)).save(korisnik);
    }

    @Test
    public void testUpdate() {
        Korisnik korisnik = new Korisnik();
        when(korisnikRepository.save(korisnik)).thenReturn(korisnik);

        Korisnik result = korisnikService.update(korisnik);

        assertEquals(korisnik, result);
        verify(korisnikRepository, times(1)).save(korisnik);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(korisnikRepository).deleteById(id);

        korisnikService.deleteById(id);

        verify(korisnikRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int id = 999;
        doThrow(EmptyResultDataAccessException.class).when(korisnikRepository).deleteById(id);

        assertThrows(EmptyResultDataAccessException.class, () -> korisnikService.deleteById(id));
        verify(korisnikRepository, times(1)).deleteById(id);
    }

    @Test
    public void testLoginSuccess() {
        String email = "test@example.com";
        String password = "password";
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnikEmail(email);

        when(korisnikRepository.findByEmailAndSifra(email, password)).thenReturn(Optional.of(korisnik));
        String token = "fake-jwt-token";
        mockStatic(JwtTool.class);
        when(JwtTool.generateToken(email)).thenReturn(token);

        ResponseEntity<?> response = korisnikService.login(email, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals(korisnik, responseBody.get("korisnik"));
        assertEquals(token, responseBody.get("jwt"));

        verify(korisnikRepository, times(1)).findByEmailAndSifra(email, password);
    }

    @Test
    public void testLoginFailure() {
        String email = "test@example.com";
        String password = "password";

        when(korisnikRepository.findByEmailAndSifra(email, password)).thenReturn(Optional.empty());

        ResponseEntity<?> response = korisnikService.login(email, password);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Login failed", response.getBody());

        verify(korisnikRepository, times(1)).findByEmailAndSifra(email, password);
    }

    @Test
    public void testLoginException() {
        String email = "test@example.com";
        String password = "password";

        when(korisnikRepository.findByEmailAndSifra(email, password)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = korisnikService.login(email, password);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        verify(korisnikRepository, times(1)).findByEmailAndSifra(email, password);
    }

    @Test
    public void testEmailExists() {
        String email = "test@example.com";
        when(korisnikRepository.findByEmail(email)).thenReturn(Optional.of(new Korisnik()));

        boolean exists = korisnikService.emailExists(email);

        assertTrue(exists);
        verify(korisnikRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testEmailNotExists() {
        String email = "test@example.com";
        when(korisnikRepository.findByEmail(email)).thenReturn(Optional.empty());

        boolean exists = korisnikService.emailExists(email);

        assertFalse(exists);
        verify(korisnikRepository, times(1)).findByEmail(email);
    }
}
