package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.Recenzija;
import com.it355.IT355_PZ.service.RecenzijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/recenzija")
@RequiredArgsConstructor
public class RecenzijaController {
    private final RecenzijaService recenzijaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Recenzija>> getAll(){
        return ResponseEntity.ok(recenzijaService.findAll());
    }

    @GetMapping("/{recenzijaId}")
    public ResponseEntity<Recenzija> getById(@PathVariable Integer recenzijaId){
        return ResponseEntity.ok(recenzijaService.findById(recenzijaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recenzijaNotFound")));
    }

    @PostMapping("/addRecenzija")
    public ResponseEntity<Recenzija> save(@RequestBody Recenzija recenzija){
        return ResponseEntity.ok(recenzijaService.save(recenzija));
    }

    @PutMapping("/updateRecenzija")
    public ResponseEntity<Recenzija> update(@RequestBody Recenzija recenzija){
        return ResponseEntity.ok(recenzijaService.update(recenzija));
    }
    @DeleteMapping("/{recenzijaId}")
    public ResponseEntity<Recenzija> deleteById(@PathVariable Integer recenzijaId){
        recenzijaService.deleteById(recenzijaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByProizvodId/{proizvodId}")
    public ResponseEntity<List<Recenzija>> getByProizvodId(@PathVariable Integer proizvodId){
        return  ResponseEntity.ok(recenzijaService.findByProizvodId(proizvodId));
    }
}
