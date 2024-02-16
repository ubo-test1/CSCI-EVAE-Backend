package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
}
