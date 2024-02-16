package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RubQuesRepository extends JpaRepository<RubriqueQuestion,Long> {
    List<RubriqueQuestion> findAllByIdRubrique(Rubrique rubrique);
}
