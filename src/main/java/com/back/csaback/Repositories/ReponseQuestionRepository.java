package com.back.csaback.Repositories;

import com.back.csaback.Models.ReponseEvaluation;
import com.back.csaback.Models.ReponseQuestion;
import com.back.csaback.Models.ReponseQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Collection;

public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion, ReponseQuestionId> {
    List<ReponseQuestion> findAllByIdReponseEvaluationIn(Collection<ReponseEvaluation> idReponseEvaluation);

    public List<ReponseQuestion> findAllByIdReponseEvaluation(ReponseEvaluation re);
}
