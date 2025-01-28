package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.KorpaStavka;

import java.util.List;
import java.util.Optional;

public interface KorpaStavkaService {
    List<KorpaStavka> findAll();
    Optional<KorpaStavka> findById(Integer korpaStavkaId);
    KorpaStavka save(KorpaStavka korpaStavka);
    KorpaStavka update(KorpaStavka korpaStavka);
    void deleteById(Integer korpaStavkaId);

    List<KorpaStavka> findByKorpa_Korisnik_Id(Integer korisnikId);
    void deleteByKorpa_Korisnik_Id(Integer korisnikId);
}
