package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Korpa;

import java.util.List;
import java.util.Optional;

public interface KorpaService {
    List<Korpa> findAll();
    Optional<Korpa> findById(Integer korpa);
    Korpa save(Korpa korpa);
    Korpa update(Korpa korpa);
    void deleteById(Integer korpaId);
    Optional<Korpa> findByKorisnik_Id(Integer korisnikId);

}
