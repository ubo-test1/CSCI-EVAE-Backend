package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question_evaluation", schema = "csa_db")
public class QuestionEvaluation {
    @Id
    @Column(name = "ID_QUESTION_EVALUATION", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RUBRIQUE_EVALUATION", nullable = false)
    private RubriqueEvaluation idRubriqueEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_QUESTION")
    private Question idQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_QUALIFICATIF")
    private Qualificatif idQualificatif;

    @Column(name = "ORDRE", nullable = false)
    private Byte ordre;

    @Column(name = "INTITULE", length = 64)
    private String intitule;

}