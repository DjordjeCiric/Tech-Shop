package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.Proizvod;
import com.it355.IT355_PZ.service.ProizvodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/proizvod")
@RequiredArgsConstructor
public class ProizvodController {
    private final ProizvodService proizvodService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Proizvod>> getAll(){
        return ResponseEntity.ok(proizvodService.findAll());
    }

    @GetMapping("/{proizvodId}")
    public ResponseEntity<Proizvod> getById(@PathVariable Integer proizvodId){
        return ResponseEntity.ok(proizvodService.findById(proizvodId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "proizvodNotFound")));
    }

    @PostMapping("/addProizvod")
    public ResponseEntity<Proizvod> save(@RequestBody Proizvod proizvod){
        return ResponseEntity.ok(proizvodService.save(proizvod));
    }

    @PutMapping("/updateProizvod")
    public ResponseEntity<Proizvod> update(@RequestBody Proizvod proizvod){
        return ResponseEntity.ok(proizvodService.update(proizvod));
    }

    @DeleteMapping("/{proizvodId}")
    public ResponseEntity<Proizvod> deleteById(@PathVariable Integer proizvodId){
        proizvodService.deleteById(proizvodId);
        return ResponseEntity.ok().build();
    }
}
