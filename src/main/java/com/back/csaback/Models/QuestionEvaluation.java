package com.back.csaback.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "QUESTION_EVALUATION")
public class QuestionEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quev_generator")
    @SequenceGenerator(name="quev_generator", sequenceName = "QEV_SEQ", allocationSize=1)
    @Column(name = "ID_QUESTION_EVALUATION", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_RUBRIQUE_EVALUATION", nullable = false)
    private RubriqueEvaluation idRubriqueEvaluation;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_QUESTION")
    private Question idQuestion;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_QUALIFICATIF")
    private Qualificatif idQualificatif;

    @NotNull
    @Column(name = "ORDRE", nullable = false)
    private Short ordre;

    @Size(max = 64)
    @Column(name = "INTITULE", length = 64)
    private String intitule;

    @Override
    public String toString() {
        return "QuestionEvaluation{" +
                "id=" + id +
                ", idRubriqueEvaluation=" + idRubriqueEvaluation +
                ", idQuestion=" + idQuestion +
                ", idQualificatif=" + idQualificatif +
                ", ordre=" + ordre +
                ", intitule='" + intitule + '\'' +
                '}';
    }
}