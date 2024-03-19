package com.back.csaback.Repositories;


import com.back.csaback.Models.*;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface QuestionEvaluationRepository extends JpaRepository<QuestionEvaluation, Integer> {
    boolean existsByIdQuestionAndIdRubriqueEvaluation(Question idQuestion, @NotNull RubriqueEvaluation idRubriqueEvaluation);
    List<QuestionEvaluation> findAllByidRubriqueEvaluation(@NotNull RubriqueEvaluation idRubriqueEvaluation);
    List<QuestionEvaluation> findAllByIdRubriqueEvaluationIn(Collection<RubriqueEvaluation> idRubriqueEvaluation);
    @Query("SELECT MAX(qe.ordre) FROM QuestionEvaluation qe WHERE qe.idRubriqueEvaluation.id = :idRubriqueEvaluation")
    Short findLatestOrdreByRubriqueEvaluationId(@Param("idRubriqueEvaluation") Integer idRubriqueEvaluation);

    @Transactional
    @Modifying
    @Query("DELETE FROM QuestionEvaluation qe WHERE qe.idRubriqueEvaluation.id = :idRubriqueEvaluation")
    void deleteAllByIdRubriqueEvaluation(Integer idRubriqueEvaluation);
}

