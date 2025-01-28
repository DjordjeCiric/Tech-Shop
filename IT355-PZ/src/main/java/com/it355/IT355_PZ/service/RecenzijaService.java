package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Recenzija;

import java.util.List;
import java.util.Optional;

public interface RecenzijaService {
    List<Recenzija> findAll();
    Optional<Recenzija> findById(Integer recenzijaId);
    Recenzija save(Recenzija recenzija);
    Recenzija update(Recenzija recenzija);
    void deleteById(Integer recenzijaId);
    List<Recenzija> findByProizvodId(Integer proizvodId);
}
