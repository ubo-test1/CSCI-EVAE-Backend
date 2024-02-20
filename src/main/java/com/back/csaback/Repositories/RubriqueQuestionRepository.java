package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import java.util.List;
import java.util.Optional;

import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RubriqueQuestionRepository extends JpaRepository<RubriqueQuestion, RubriqueQuestionId> {

    boolean existsByIdRubriqueAndIdQuestion(Rubrique rubrique, Question question);
    void deleteAllByIdRubrique(Rubrique rubrique);
    List<RubriqueQuestion> findAllByIdRubrique(Rubrique idRubrique);
    @Query(value = "SELECT * FROM RUBRIQUE_QUESTION r WHERE r.ID_RUBRIQUE = :idRubrique AND r.ID_QUESTION = :idQuestion", nativeQuery = true)
    Optional<RubriqueQuestion> findByRubQuest(@Param("idRubrique") Integer idRubrique, @Param("idQuestion") Integer idQuestion);


}

