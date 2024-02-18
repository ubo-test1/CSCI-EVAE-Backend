package com.back.csaback.Requests;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RubriqueDetails {
    private Rubrique rubrique;
    private List<Question> questions;

    public RubriqueDetails(){

    }

    public RubriqueDetails(Rubrique rubrique, List<Question> questions) {
        this.rubrique = rubrique;
        this.questions = questions;
    }
}
