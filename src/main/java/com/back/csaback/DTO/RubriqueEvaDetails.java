package com.back.csaback.DTO;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RubriqueEvaDetails {
    private RubriqueEvaluation rubrique;
    private List<QuestionEvaluation> questions;

    public RubriqueEvaDetails(){

    }

    public RubriqueEvaDetails(RubriqueEvaluation rubrique, List<QuestionEvaluation> questions) {
        this.rubrique = rubrique;
        this.questions = questions;
    }
}
