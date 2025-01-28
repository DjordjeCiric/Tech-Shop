package com.it355.IT355_PZ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "proizvod")
@RequiredArgsConstructor
public class Proizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proizvod_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "proizvod_ime", nullable = false)
    private String proizvodIme;

    @Lob
    @Column(name = "proizvod_image_url", nullable = false)
    private String proizvodImageUrl;

    @Column(name = "cena", nullable = false)
    private Double cena;

    @Lob
    @Column(name = "opis", nullable = false)
    private String opis;

    @Lob
    @Column(name = "tip", nullable = false)
    private String tip;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proizvodjac_id", nullable = false)
    private Proizvodjac proizvodjac;

}