package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Promotion;
import com.back.csaback.Models.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    public List<Evaluation> findAllByPromotion(Promotion pro);

    @Query("SELECT e FROM Evaluation e WHERE e.promotion = :promotion AND e.etat <> 'ELA'")
    List<Evaluation> findAllByPromotionAndNotEtatELA(@Param("promotion") Promotion promotion);
}
