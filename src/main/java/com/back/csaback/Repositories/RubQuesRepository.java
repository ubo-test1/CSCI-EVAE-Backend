package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RubQuesRepository extends JpaRepository<RubriqueQuestion,Long> {
    List<RubriqueQuestion> findAllByIdRubrique(Rubrique rubrique);

    @Query("SELECT MAX(rq.ordre) FROM RubriqueQuestion rq WHERE rq.idRubrique.id = :rubriqueId")
    Long findMaxOrdreByRubriqueId(Integer rubriqueId);

    @Query("SELECT rq.idQuestion FROM RubriqueQuestion rq WHERE rq.idRubrique.id = :idRubrique")
    List<Question> findQuestionsByRubriqueId(Integer idRubrique);
}
