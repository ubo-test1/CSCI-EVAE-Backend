package com.back.csaback.Repositories;

import com.back.csaback.Models.ReponseEvaluation;
import com.back.csaback.Models.ReponseQuestion;
import com.back.csaback.Models.ReponseQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion, ReponseQuestionId> {
    public List<ReponseQuestion> findAllByIdReponseEvaluation(ReponseEvaluation re);
}
