package com.back.csaback.Repositories;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RubQuesRepository extends JpaRepository<RubriqueQuestion,Long> {
    List<RubriqueQuestion> findAllByIdRubrique(Rubrique rubrique);
}
