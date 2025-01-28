package com.it355.IT355_PZ.controller;

import com.it355.IT355_PZ.entity.Proizvodjac;
import com.it355.IT355_PZ.service.ProizvodjacService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/proizvodjac")
@RequiredArgsConstructor
public class ProizvodjacController {
    private final ProizvodjacService proizvodjacService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Proizvodjac>> getAll(){
        return ResponseEntity.ok(proizvodjacService.findAll());
    }

    @GetMapping("/{proizvodjacId}")
    public ResponseEntity<Proizvodjac> getById(@PathVariable Integer proizvodjacId){
        return ResponseEntity.ok(proizvodjacService.findById(proizvodjacId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "proizvodjacNotFound")));
    }

    @PostMapping("/addProizvodjac")
    public ResponseEntity<Proizvodjac> save(@RequestBody Proizvodjac proizvodjac){
        return ResponseEntity.ok(proizvodjacService.save(proizvodjac));
    }

    @PutMapping("/updateProizvodjac")
    public ResponseEntity<Proizvodjac> update(@RequestBody Proizvodjac proizvodjac){
        return ResponseEntity.ok(proizvodjacService.update(proizvodjac));
    }
    @DeleteMapping("/{proizvodjacId}")
    public ResponseEntity<Proizvodjac> deleteById(@PathVariable Integer proizvodjacId){
        proizvodjacService.deleteById(proizvodjacId);
        return ResponseEntity.ok().build();
    }
}
