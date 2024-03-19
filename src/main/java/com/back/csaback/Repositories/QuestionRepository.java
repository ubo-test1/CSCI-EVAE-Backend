package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
   // boolean existsByIntitule(String intitule);
   @Query("SELECT CASE WHEN COUNT(q) > 0 THEN TRUE ELSE FALSE END FROM Question q WHERE LOWER(q.intitule) = LOWER(:intitule)")
   boolean existsByIntitule(@Param("intitule") String intitule);
}
