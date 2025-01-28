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
@Table(name = "recenzija")
@RequiredArgsConstructor
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recenzija_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvod proizvod;

    @Lob
    @Column(name = "recenzija", nullable = false)
    private String recenzija;

}