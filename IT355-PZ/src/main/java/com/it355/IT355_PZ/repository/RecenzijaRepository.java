package com.it355.IT355_PZ.repository;

import com.it355.IT355_PZ.entity.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecenzijaRepository extends JpaRepository<Recenzija, Integer> {
    @Query("select r from Recenzija r where r.proizvod.id = ?1")
    List<Recenzija> findByProizvod_Id(Integer id);
}