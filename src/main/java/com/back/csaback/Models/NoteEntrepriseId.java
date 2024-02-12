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
public class NoteEntrepriseId implements Serializable {
    private static final long serialVersionUID = -1487221593418061362L;
    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @Column(name = "ANNEE_PRO", nullable = false, length = 10)
    private String anneePro;

    @Column(name = "CRITE_ID", nullable = false)
    private Double criteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteEntrepriseId entity = (NoteEntrepriseId) o;
        return Objects.equals(this.noEtudiantNat, entity.noEtudiantNat) &&
                Objects.equals(this.criteId, entity.criteId) &&
                Objects.equals(this.anneePro, entity.anneePro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEtudiantNat, criteId, anneePro);
    }

}