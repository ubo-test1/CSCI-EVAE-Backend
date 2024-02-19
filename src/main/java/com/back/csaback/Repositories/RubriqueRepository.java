package com.back.csaback.Repositories;

import com.back.csaback.Models.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RubriqueRepository extends JpaRepository<Rubrique,Integer> {
    @Query("SELECT COALESCE(MAX(r.ordre), 0) FROM Rubrique r")
    Long findMaxOrdre();

    public Boolean existsByDesignation(String des);
}
