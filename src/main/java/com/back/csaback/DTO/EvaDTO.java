package com.back.csaback.DTO;

import com.back.csaback.Models.Evaluation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaDTO {
    private Integer id;

    private Short noEvaluation;

    private String designation;

    private String etat;

    private String periode;

    private LocalDate debutReponse;

    private LocalDate finReponse;

    public EvaDTO(){

    }

    public EvaDTO(Evaluation e){
        this.id = e.getId();
        this.noEvaluation = e.getNoEvaluation();
        this.designation = e.getDesignation();
        this.etat = e.getEtat();
        this.periode = e.getPeriode();
        this.debutReponse = e.getDebutReponse();
        this.finReponse = e.getFinReponse();
    }
}