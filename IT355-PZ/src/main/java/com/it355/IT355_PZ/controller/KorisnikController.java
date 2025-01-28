package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.Korisnik;
import com.it355.IT355_PZ.entity.Korpa;
import com.it355.IT355_PZ.service.KorisnikService;
import com.it355.IT355_PZ.service.KorpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/korisnik")
@RequiredArgsConstructor
public class KorisnikController {
    private final KorisnikService korisnikService;
    private final KorpaService korpaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Korisnik>> getAll(){
        return ResponseEntity.ok(korisnikService.findAll());
    }

    @GetMapping("/{korisnikId}")
    public ResponseEntity<Korisnik> getById(@PathVariable Integer korisnikId){
        return ResponseEntity.ok(korisnikService.findById(korisnikId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "korisnikNotFound")));
    }

    @PostMapping("/addKorisnik")
    public ResponseEntity<Korisnik> save(@RequestBody Korisnik korisnik){
        Korisnik savedKorisnik = korisnikService.save(korisnik);


        Korpa korpa = new Korpa();
        korpa.setKorisnik(savedKorisnik);
        korpaService.save(korpa);

        return ResponseEntity.ok(savedKorisnik);
    }

    @PutMapping("/updateKorisnik")
    public ResponseEntity<Korisnik> update(@RequestBody Korisnik korisnik){
        return ResponseEntity.ok(korisnikService.update(korisnik));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Korisnik korisnik){
        return korisnikService.login(korisnik.getKorisnikEmail(), korisnik.getKorisnikSifra());
    }
    @DeleteMapping("/{korisnikId}")
    public ResponseEntity<Korisnik> deleteById(@PathVariable Integer korisnikId){
        korisnikService.deleteById(korisnikId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/checkEmailExists")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = korisnikService.emailExists(email);
        return ResponseEntity.ok(exists);
    }
}
