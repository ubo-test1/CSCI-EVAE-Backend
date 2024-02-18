package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ReponseQuestionId implements Serializable {
    private static final long serialVersionUID = 2380091440203249693L;
    @NotNull
    @Column(name = "ID_REPONSE_EVALUATION", nullable = false)
    private Integer idReponseEvaluation;

    @NotNull
    @Column(name = "ID_QUESTION_EVALUATION", nullable = false)
    private Integer idQuestionEvaluation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReponseQuestionId entity = (ReponseQuestionId) o;
        return Objects.equals(this.idReponseEvaluation, entity.idReponseEvaluation) &&
                Objects.equals(this.idQuestionEvaluation, entity.idQuestionEvaluation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReponseEvaluation, idQuestionEvaluation);
    }

}