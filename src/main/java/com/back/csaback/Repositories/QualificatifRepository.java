package com.back.csaback.Repositories;

import com.back.csaback.Models.Qualificatif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QualificatifRepository extends JpaRepository<Qualificatif, Integer> {


    boolean existsByMinimalAndMaximal(String minimal, String maximal);



}
