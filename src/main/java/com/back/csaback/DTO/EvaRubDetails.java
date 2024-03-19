package com.back.csaback.DTO;

import com.back.csaback.Models.Evaluation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EvaRubDetails {
    private Evaluation evaluation;
    private List<RubriqueEvaDetails> rubriques;

    public EvaRubDetails(){

    }

    public EvaRubDetails(Evaluation evaluation, List<RubriqueEvaDetails> rubriques) {
        this.evaluation = evaluation;
        this.rubriques = rubriques;
    }
}
