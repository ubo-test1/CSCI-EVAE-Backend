package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "REPONSE_QUESTION")
public class ReponseQuestion {
    @EmbeddedId
    private ReponseQuestionId id;

    @MapsId("idReponseEvaluation")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_REPONSE_EVALUATION", nullable = false)
    private ReponseEvaluation idReponseEvaluation;

    @MapsId("idQuestionEvaluation")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_QUESTION_EVALUATION", nullable = false)
    private QuestionEvaluation idQuestionEvaluation;

    @Column(name = "POSITIONNEMENT")
    private Long positionnement;

    @Override
    public String toString() {
        return "ReponseQuestion{" +
                "id=" + id +
                ", idReponseEvaluation=" + idReponseEvaluation +
                ", idQuestionEvaluation=" + idQuestionEvaluation +
                ", positionnement=" + positionnement +
                '}';
    }
}