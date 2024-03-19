package com.back.csaback.Repositories;

import com.back.csaback.Models.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RubriqueRepository extends JpaRepository<Rubrique,Integer> {
    @Query("SELECT COALESCE(MAX(r.ordre), 0) FROM Rubrique r")
    Long findMaxOrdre();

    //public Boolean existsByDesignation(String des);
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Rubrique r WHERE LOWER(r.designation) = LOWER(:des)")
    Boolean existsByDesignation(@Param("des") String des);
}
