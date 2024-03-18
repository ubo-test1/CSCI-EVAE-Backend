package com.back.csaback.DTO;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Formation;
import com.back.csaback.Models.Promotion;
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

    private String Code_UE;

    private String Code_EC;

    private Promotion promotion;

    private Formation codeFormation;

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
        if(e.getElementConstitutif()==null) this.Code_EC=null;
        else this.Code_EC=e.getElementConstitutif().getId().getCodeEc();
        this.Code_UE=e.getUniteEnseignement().getId().getCodeUe();
        this.promotion=e.getPromotion();
        this.codeFormation=e.getUniteEnseignement().getCodeFormation();
    }
}
