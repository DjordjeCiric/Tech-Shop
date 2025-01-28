package com.it355.IT355_PZ.repository;

import com.it355.IT355_PZ.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    @Query("select k from Korisnik k where k.korisnikEmail = ?1")
    Optional<Korisnik> findByEmail(String email);

    @Query("select k from Korisnik k where k.korisnikEmail = ?1 and k.korisnikSifra = ?2")
    Optional<Korisnik> findByEmailAndSifra(String email, String sifra);
}