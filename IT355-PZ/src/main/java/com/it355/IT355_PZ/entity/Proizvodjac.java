package com.it355.IT355_PZ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proizvodjac")
@RequiredArgsConstructor

public class Proizvodjac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proizvodjac_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "proizvodjac_ime", nullable = false)
    private String proizvodjacIme;

    @Lob
    @Column(name = "opis", nullable = false)
    private String opis;

}