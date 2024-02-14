//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN true ELSE false END FROM Question q WHERE q.idQualificatif.idQualificatif = :idQualificatif")
    boolean existsByQualificatifId(@Param("idQualificatif") Long idQualificatif);
}
