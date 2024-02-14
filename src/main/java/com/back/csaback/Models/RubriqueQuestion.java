package com.back.csaback.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rubrique_question")
public class RubriqueQuestion {
    @EmbeddedId
    private RubriqueQuestionId id;

    @MapsId("idRubrique")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_RUBRIQUE", nullable = false)
    private Rubrique idRubrique;

    @MapsId("idQuestion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_QUESTION", nullable = false)
    private Question idQuestion;

    @Column(name = "ORDRE", nullable = false, precision = 38)
    private Long ordre;


    public RubriqueQuestion(){}
    public RubriqueQuestion(RubriqueQuestionId rubriqueQuestionId,Rubrique rubrique,Question question, Long ordre) {
       this.id=rubriqueQuestionId;
       this.ordre=ordre;
    }
}