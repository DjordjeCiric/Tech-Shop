package com.it355.IT355_PZ.repository;

import com.it355.IT355_PZ.entity.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KorpaRepository extends JpaRepository<Korpa, Integer> {

    @Query("select k from Korpa k where k.korisnik.id = ?1")
    Optional<Korpa> findByKorisnik_Id(Integer id);
}