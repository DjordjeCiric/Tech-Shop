package com.it355.IT355_PZ.repository;

import com.it355.IT355_PZ.entity.KorpaStavka;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KorpaStavkaRepository extends JpaRepository<KorpaStavka, Integer> {
    @Query("select k from KorpaStavka k where k.korpa.korisnik.id = ?1")
    List<KorpaStavka> findByKorpa_Korisnik_Id(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM KorpaStavka kk WHERE kk.korpa.id IN (SELECT k.id FROM Korpa k WHERE k.korisnik.id = ?1)")
    void deleteByKorpa_Korisnik_Id(Integer korisnikId);
}