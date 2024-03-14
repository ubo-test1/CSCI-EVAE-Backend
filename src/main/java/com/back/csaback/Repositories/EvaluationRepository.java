package com.back.csaback.Repositories;

import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    public List<Evaluation> findAllByPromotion(Promotion pro);
    @Query("SELECT e FROM Evaluation e WHERE e.promotion = :promotion AND e.etat <> 'ELA'")
    List<Evaluation> findAllByPromotionAndNotEtatELA(@Param("promotion") Promotion promotion);
    @Query(value = "SELECT MAX(noEvaluation) FROM Evaluation")
    Short findNoEvaluationMax();
    @Query("SELECT e FROM Evaluation e WHERE e.id = :id")
    Optional<Evaluation> findByCustomQuery(@Param("id") Integer id);

    List<Evaluation> findAllByNoEnseignant(Enseignant noEnseignant);

}
