package com.back.csaback.Repositories;

import com.back.csaback.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion, ReponseQuestionId> {
    List<ReponseQuestion> findAllByIdReponseEvaluationIn(Collection<ReponseEvaluation> idReponseEvaluation);

}
