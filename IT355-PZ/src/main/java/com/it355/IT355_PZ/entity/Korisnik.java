package com.it355.IT355_PZ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "korisnik")
@RequiredArgsConstructor
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "korisnik_ime", nullable = false)
    private String korisnikIme;

    @Lob
    @Column(name = "korisnik_email", nullable = false)
    private String korisnikEmail;

    @Lob
    @Column(name = "korisnik_sifra", nullable = false)
    private String korisnikSifra;

    @Column(name = "admin", nullable = false)
    private Byte admin;

}