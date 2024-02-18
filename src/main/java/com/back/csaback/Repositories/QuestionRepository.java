package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByIntitule(String intitule);

}


