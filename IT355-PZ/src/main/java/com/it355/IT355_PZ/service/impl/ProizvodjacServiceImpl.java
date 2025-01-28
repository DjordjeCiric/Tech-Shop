package com.it355.IT355_PZ.service.impl;

import com.it355.IT355_PZ.entity.Proizvodjac;
import com.it355.IT355_PZ.repository.ProizvodjacRepository;
import com.it355.IT355_PZ.service.ProizvodjacService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProizvodjacServiceImpl implements ProizvodjacService {

    private final ProizvodjacRepository proizvodjacRepository;
    @Override
    public List<Proizvodjac> findAll() {
        return proizvodjacRepository.findAll();
    }

    @Override
    public Optional<Proizvodjac> findById(Integer proizvodjacId) {
        return proizvodjacRepository.findById(proizvodjacId);
    }

    @Override
    public Proizvodjac save(Proizvodjac proizvodjac) {
        return proizvodjacRepository.save(proizvodjac);
    }

    @Override
    public Proizvodjac update(Proizvodjac proizvodjac) {
        return proizvodjacRepository.save(proizvodjac);
    }

    @Override
    public void deleteById(Integer proizvodjacId) {
        proizvodjacRepository.deleteById(proizvodjacId);
    }
}
