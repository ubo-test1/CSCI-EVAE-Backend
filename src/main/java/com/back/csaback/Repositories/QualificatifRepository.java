package com.back.csaback.Repositories;

import com.back.csaback.Models.Qualificatif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface QualificatifRepository extends JpaRepository<Qualificatif, Integer> {


    //boolean existsByMinimalAndMaximal(String minimal, String maximal);
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN TRUE ELSE FALSE END FROM Qualificatif q WHERE LOWER(q.minimal) = LOWER(:minimal) AND LOWER(q.maximal) = LOWER(:maximal)")
    boolean existsByMinimalAndMaximal(@Param("minimal") String minimal, @Param("maximal") String maximal);


}
