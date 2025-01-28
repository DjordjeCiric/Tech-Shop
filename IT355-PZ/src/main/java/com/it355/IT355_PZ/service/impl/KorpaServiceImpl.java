package com.it355.IT355_PZ.service.impl;

import com.it355.IT355_PZ.entity.Korpa;
import com.it355.IT355_PZ.repository.KorpaRepository;
import com.it355.IT355_PZ.service.KorpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class KorpaServiceImpl implements KorpaService {

    private final KorpaRepository korpaRepository;

    @Override
    public List<Korpa> findAll() {
        return korpaRepository.findAll();
    }

    @Override
    public Optional<Korpa> findById(Integer korpa) {
        return korpaRepository.findById(korpa);
    }

    @Override
    public Korpa save(Korpa korpa) {
        return korpaRepository.save(korpa);
    }

    @Override
    public Korpa update(Korpa korpa) {
        return korpaRepository.save(korpa);
    }

    @Override
    public void deleteById(Integer korpaId) {
        korpaRepository.deleteById(korpaId);
    }

    @Override
    public Optional<Korpa> findByKorisnik_Id(Integer korisnikId) {
        return korpaRepository.findByKorisnik_Id(korisnikId);
    }

}
