package com.it355.IT355_PZ.service;

import com.it355.IT355_PZ.entity.Proizvodjac;

import java.util.List;
import java.util.Optional;

public interface ProizvodjacService {
    List<Proizvodjac> findAll();
    Optional<Proizvodjac> findById(Integer proizvodjacId);
    Proizvodjac save(Proizvodjac proizvodjac);
    Proizvodjac update(Proizvodjac proizvodjac);
    void deleteById(Integer proizvodjacId);
}
