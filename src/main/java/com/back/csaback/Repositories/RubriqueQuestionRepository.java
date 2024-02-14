package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RubriqueQuestionRepository extends JpaRepository<RubriqueQuestion, RubriqueQuestionId> {

    boolean existsByIdRubriqueAndIdQuestion(Rubrique rubrique, Question question);
    void deleteAllByIdRubrique(Rubrique rubrique);
    List<RubriqueQuestion> findAllByIdRubrique(Rubrique idRubrique);

    void updateByOrdre(Long ordre);

}
