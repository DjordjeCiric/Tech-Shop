package com.it355.IT355_PZ.service.impl;

import com.it355.IT355_PZ.entity.Recenzija;
import com.it355.IT355_PZ.repository.RecenzijaRepository;
import com.it355.IT355_PZ.service.RecenzijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RecenzijaServiceImpl implements RecenzijaService {

    private final RecenzijaRepository recenzijaRepository;
    @Override
    public List<Recenzija> findAll() {
        return recenzijaRepository.findAll();
    }

    @Override
    public Optional<Recenzija> findById(Integer recenzijaId) {
        return recenzijaRepository.findById(recenzijaId);
    }

    @Override
    public Recenzija save(Recenzija recenzija) {
        return recenzijaRepository.save(recenzija);
    }

    @Override
    public Recenzija update(Recenzija recenzija) {
        return recenzijaRepository.save(recenzija);
    }

    @Override
    public void deleteById(Integer recenzijaId) {
        recenzijaRepository.deleteById(recenzijaId);
    }

    @Override
    public List<Recenzija> findByProizvodId(Integer proizvodId) {
        return recenzijaRepository.findByProizvod_Id(proizvodId);
    }
}
