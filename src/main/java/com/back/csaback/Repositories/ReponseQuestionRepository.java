package com.back.csaback.Repositories;

import com.back.csaback.Models.ReponseQuestion;
import com.back.csaback.Models.ReponseQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion, ReponseQuestionId> {
}
