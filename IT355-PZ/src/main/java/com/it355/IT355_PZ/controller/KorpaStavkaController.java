package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.KorpaStavka;
import com.it355.IT355_PZ.service.KorpaStavkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/korpaStavka")
@RequiredArgsConstructor
public class KorpaStavkaController {
    private final KorpaStavkaService korpaStavkaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<KorpaStavka>> getAll(){
        return ResponseEntity.ok(korpaStavkaService.findAll());
    }

    @GetMapping("/findByKorpa_Korisnik_Id/{korisnikId}")
    public ResponseEntity<List<KorpaStavka>> findByKorpa_Korisnik_Id(@PathVariable Integer korisnikId){
        return ResponseEntity.ok(korpaStavkaService.findByKorpa_Korisnik_Id(korisnikId));
    }

    @GetMapping("/{korpaStavkaId}")
    public ResponseEntity<KorpaStavka> getById(@PathVariable Integer korpaStavkaId){
        return ResponseEntity.ok(korpaStavkaService.findById(korpaStavkaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "korpaStavkaNotFound")));
    }

    @PostMapping("/addKorpaStavka")
    public ResponseEntity<KorpaStavka> save(@RequestBody KorpaStavka korpaStavka){
        return ResponseEntity.ok(korpaStavkaService.save(korpaStavka));
    }

    @PutMapping("/updateKorpaStavka")
    public ResponseEntity<KorpaStavka> update(@RequestBody KorpaStavka korpaStavka){
        return ResponseEntity.ok(korpaStavkaService.update(korpaStavka));
    }
    @DeleteMapping("/{korpaStavkaId}")
    public ResponseEntity<KorpaStavka> deleteById(@PathVariable Integer korpaStavkaId){
        korpaStavkaService.deleteById(korpaStavkaId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/deleteByKorpa_Korisnik_Id/{korisnikId}")
    public ResponseEntity<Void> deleteByKorpa_Korisnik_Id(@PathVariable Integer korisnikId) {
        korpaStavkaService.deleteByKorpa_Korisnik_Id(korisnikId);
        return ResponseEntity.ok().build();
    }
}
