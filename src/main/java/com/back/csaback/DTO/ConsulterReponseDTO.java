package com.back.csaback.DTO;

import com.back.csaback.Models.ReponseQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConsulterReponseDTO {
    private String commentaireEvaluation;
    private List<ReponseQuestion> questions;

    public ConsulterReponseDTO(){

    }
}
