package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Models.RubriqueQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RubriqueEvaluationRepository extends JpaRepository<RubriqueEvaluation,Integer> {
    List<RubriqueEvaluation> findAllByIdRubrique(Rubrique idRubrique);
    List<RubriqueEvaluation> findAllByIdEvaluation(Evaluation evaluation);
    boolean existsRubriqueEvaluationByIdRubriqueAndIdEvaluation(Rubrique rubrique,Evaluation evaluation);
    @Query("SELECT re FROM RubriqueEvaluation re WHERE re.idEvaluation = :evaluation")
    List<RubriqueEvaluation> findByEvaluation(@Param("evaluation") Evaluation eval);
    @Query("SELECT re FROM RubriqueEvaluation re WHERE re.idRubrique = :rubrique AND re.idEvaluation = :evaluation")
    List<RubriqueEvaluation> findByIdRubriqueAndIdEval(@Param("rubrique") Rubrique rubrique, @Param("evaluation") Evaluation evaluation);
    @Query(value = "SELECT * FROM RUBRIQUE_EVALUATION", nativeQuery = true)
    List<RubriqueEvaluation> findAllCustom();
    @Query("SELECT MAX(re.ordre) FROM RubriqueEvaluation re WHERE re.idEvaluation.id = :evaluationId")
    Short findLastOrdreByEvaluationId(Integer evaluationId);
}
