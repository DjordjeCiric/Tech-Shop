package com.it355.IT355_PZ.service.impl;

import com.it355.IT355_PZ.entity.KorpaStavka;
import com.it355.IT355_PZ.repository.KorpaStavkaRepository;
import com.it355.IT355_PZ.service.KorpaStavkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class KorpaStavkaServiceImpl implements KorpaStavkaService {

    private final KorpaStavkaRepository korpaStavkaRepository;
    @Override
    public List<KorpaStavka> findAll() {
        return korpaStavkaRepository.findAll();
    }

    @Override
    public Optional<KorpaStavka> findById(Integer korpaStavkaId) {
        return korpaStavkaRepository.findById(korpaStavkaId);
    }

    @Override
    public KorpaStavka save(KorpaStavka korpaStavka) {
        return korpaStavkaRepository.save(korpaStavka);
    }

    @Override
    public KorpaStavka update(KorpaStavka korpaStavka) {
        return korpaStavkaRepository.save(korpaStavka);
    }

    @Override
    public void deleteById(Integer korpaStavkaId) {
        korpaStavkaRepository.deleteById(korpaStavkaId);
    }

    @Override
    public List<KorpaStavka> findByKorpa_Korisnik_Id(Integer korisnikId) {
        return korpaStavkaRepository.findByKorpa_Korisnik_Id(korisnikId);
    }

    @Override
    public void deleteByKorpa_Korisnik_Id(Integer korisnikId) {
        korpaStavkaRepository.deleteByKorpa_Korisnik_Id(korisnikId);
    }
}
