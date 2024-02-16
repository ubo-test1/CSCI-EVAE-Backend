//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Repositories;

import com.back.csaback.Models.Qualificatif;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificatifRepository extends JpaRepository<Qualificatif, Long> {


    boolean existsByMinimalAndMaximal(String minimal, String maximal);



}
