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
@Table(name = "korpa_stavka")
@RequiredArgsConstructor
public class KorpaStavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korpa_stavka_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "korpa_id", nullable = false)
    private Korpa korpa;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvod proizvod;

    @Column(name = "kolicina", nullable = false)
    private Integer kolicina;

}