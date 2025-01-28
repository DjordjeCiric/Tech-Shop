package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {
    List<Korisnik> findAll();
    Optional<Korisnik> findById(Integer korisnikId);
    Korisnik save(Korisnik korisnik);
    Korisnik update(Korisnik korisnik);
    void deleteById(Integer korisnikId);
    ResponseEntity<?> login(String email, String password);
    boolean emailExists(String email);
}
