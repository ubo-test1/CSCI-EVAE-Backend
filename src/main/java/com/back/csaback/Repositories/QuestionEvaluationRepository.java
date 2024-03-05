package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionEvaluationRepository extends JpaRepository<QuestionEvaluation, Integer> {
    boolean existsByIdQuestionAndIdRubriqueEvaluation(Question idQuestion, @NotNull RubriqueEvaluation idRubriqueEvaluation);
    List<QuestionEvaluation> findAllByidRubriqueEvaluation(@NotNull RubriqueEvaluation idRubriqueEvaluation);




}