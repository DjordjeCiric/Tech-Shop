package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Proizvod;

import java.util.List;
import java.util.Optional;

public interface ProizvodService {
    List<Proizvod> findAll();
    Optional<Proizvod> findById(Integer proizvodId);
    Proizvod save(Proizvod proizvod);
    Proizvod update(Proizvod proizvod);
    void deleteById(Integer proizvodId);
}
