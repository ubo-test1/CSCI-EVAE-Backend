package com.back.csaback.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "EVALUATION")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eva_generator")
    @SequenceGenerator(name="eva_generator", sequenceName = "EVE_SEQ", allocationSize=1)
    @Column(name = "ID_EVALUATION", nullable = false)
    private Integer id;

    //@NotNull(message = "L'enseignant est demandé ! ")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @NotNull(message = "L'unité enseignement est demandée ! ")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION",nullable = false),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE" ,nullable = false),
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private UniteEnseignement uniteEnseignement;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")),
            @JoinColumnOrFormula(formula=  @JoinFormula(value= "CODE_UE", referencedColumnName = "CODE_UE")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "CODE_EC", referencedColumnName = "CODE_EC", nullable = false))
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ElementConstitutif elementConstitutif;


    @NotNull(message = "une promotion est demandée !")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "ANNEE_UNIVERSITAIRE", referencedColumnName = "ANNEE_UNIVERSITAIRE", nullable = false))
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Promotion promotion;

    //@NotNull
    @Column(name = "NO_EVALUATION", nullable = false)
    private Short noEvaluation;

    @Size(max = 16)
    @NotNull(message = "la designation est demandée !")
    @Column(name = "DESIGNATION", nullable = false, length = 16)
    private String designation;

    @Size(max = 3)
    @NotNull(message = "l'état est demandé !")
    @Column(name = "etat", length = 3, nullable = false, columnDefinition = "CHAR(3)")
    private String etat;

    @Size(max = 64)
    @Column(name = "PERIODE", length = 64)
    private String periode;

    @NotNull(message = "Le debut de reponse est demandé !")
    @Column(name = "DEBUT_REPONSE", nullable = false)
    private LocalDate debutReponse;

    @NotNull(message = "La date de fin de reponse est demandée ! ")
    @Column(name = "FIN_REPONSE", nullable = false)
    private LocalDate finReponse;

    public Evaluation() {
    }

    public Evaluation(Integer id, Enseignant noEnseignant, ElementConstitutif elementConstitutif, Promotion promotion, Short noEvaluation, String designation, String etat, String periode, LocalDate debutReponse, LocalDate finReponse) {
        this.id = id;
        this.noEnseignant = noEnseignant;
        this.elementConstitutif = elementConstitutif;
        this.promotion = promotion;
        this.noEvaluation = noEvaluation;
        this.designation = designation;
        this.etat = etat;
        this.periode = periode;
        this.debutReponse = debutReponse;
        this.finReponse = finReponse;
    }

    public Evaluation(Integer id) {
        this.id = id;
    }
}
