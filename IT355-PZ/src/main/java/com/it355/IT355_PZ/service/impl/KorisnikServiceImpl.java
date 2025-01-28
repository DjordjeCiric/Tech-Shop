package com.it355.IT355_PZ.service.impl;

import com.it355.IT355_PZ.entity.Korisnik;
import com.it355.IT355_PZ.repository.KorisnikRepository;
import com.it355.IT355_PZ.service.KorisnikService;
import com.it355.IT355_PZ.tool.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    @Override
    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    @Override
    public Optional<Korisnik> findById(Integer korisnikId) {
        return korisnikRepository.findById(korisnikId);
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik update(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public void deleteById(Integer korisnikId) {
        korisnikRepository.deleteById(korisnikId);
    }

    @Override
    public ResponseEntity<?> login(String email, String sifra) {
        try {
            Optional<Korisnik> optionalKorisnik = korisnikRepository.findByEmailAndSifra(email, sifra);
            if (optionalKorisnik.isEmpty()) {
                return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
            }
            Korisnik korisnik = optionalKorisnik.get();
            String jwt = JwtTool.generateToken(korisnik.getKorisnikEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("korisnik", korisnik);
            response.put("jwt", jwt);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean emailExists(String email) {
        return korisnikRepository.findByEmail(email).isPresent();
    }
}
