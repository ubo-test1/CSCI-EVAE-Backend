package com.back.csaback.Requests;

import com.back.csaback.Models.Evaluation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EvaluationDetails {
    private Evaluation evaluation;
    private List<RubriqueDetails> rubriques;

    public EvaluationDetails(){

    }

    public EvaluationDetails(Evaluation evaluation, List<RubriqueDetails> rubriques) {
        this.evaluation = evaluation;
        this.rubriques = rubriques;
    }
}
