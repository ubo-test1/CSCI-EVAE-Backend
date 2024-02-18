package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PromotionId implements Serializable {
    private static final long serialVersionUID = 1001456934076162399L;
    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;

    @Size(max = 10)
    @NotNull
    @Column(name = "ANNEE_UNIVERSITAIRE", nullable = false, length = 10)
    private String anneeUniversitaire;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PromotionId entity = (PromotionId) o;
        return Objects.equals(this.anneeUniversitaire, entity.anneeUniversitaire) &&
                Objects.equals(this.codeFormation, entity.codeFormation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anneeUniversitaire, codeFormation);
    }

}