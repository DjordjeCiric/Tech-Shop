package com.it355.IT355_PZ.repository;

import com.it355.IT355_PZ.entity.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {
}