package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.Korpa;
import com.it355.IT355_PZ.service.KorpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/korpa")
@RequiredArgsConstructor
public class KorpaController {
    private final KorpaService korpaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Korpa>> getAll(){
        return ResponseEntity.ok(korpaService.findAll());
    }

    @GetMapping("/{korpaId}")
    public ResponseEntity<Korpa> getById(@PathVariable Integer korpaId){
        return ResponseEntity.ok(korpaService.findById(korpaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "korpaNotFound")));
    }

    @GetMapping("/getByKorisnikId/{korisnikId}")
    public ResponseEntity<Korpa> getByKorisnikId(@PathVariable Integer korisnikId){
        return ResponseEntity.ok(korpaService.findByKorisnik_Id(korisnikId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "korpaNotFound")));
    }

    @PostMapping("/addKorpa")
    public ResponseEntity<Korpa> save(@RequestBody Korpa korpa){
        return ResponseEntity.ok(korpaService.save(korpa));
    }

    @PutMapping("/updateKorpa")
    public ResponseEntity<Korpa> update(@RequestBody Korpa korpa){
        return ResponseEntity.ok(korpaService.update(korpa));
    }

    @DeleteMapping("/{korpaId}")
    public ResponseEntity<Korpa> deleteById(@PathVariable Integer korpaId){
        korpaService.deleteById(korpaId);
        return ResponseEntity.ok().build();
    }
}
