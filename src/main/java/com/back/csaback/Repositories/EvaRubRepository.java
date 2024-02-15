package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaRubRepository extends JpaRepository<RubriqueEvaluation,Long> {
    List<RubriqueEvaluation> findAllByIdEvaluation(Evaluation idEvaluation);
    List<RubriqueEvaluation> findAllByIdRubrique(Rubrique idRubrique);
}
