package com.back.csaback.Repositories;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Models.RubriqueQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RubriqueEvaluationRepository extends JpaRepository<RubriqueEvaluation,Long> {
    List<RubriqueEvaluation> findAllByIdRubrique(Rubrique idRubrique);
}
