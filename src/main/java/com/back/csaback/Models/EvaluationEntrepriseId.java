package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EvaluationEntrepriseId implements Serializable {
    private static final long serialVersionUID = 1761674983594152163L;
    @Column(name = "ANNEE_PRO", nullable = false, length = 10)
    private String anneePro;

    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EvaluationEntrepriseId entity = (EvaluationEntrepriseId) o;
        return Objects.equals(this.noEtudiantNat, entity.noEtudiantNat) &&
                Objects.equals(this.anneePro, entity.anneePro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEtudiantNat, anneePro);
    }

}