package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ContactStageId implements Serializable {
    private static final long serialVersionUID = -7616561277802095396L;
    @Column(name = "DATE_CONTACT", nullable = false)
    private Instant dateContact;

    @Column(name = "ANNEE_PRO", nullable = false, length = 10)
    private String anneePro;

    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContactStageId entity = (ContactStageId) o;
        return Objects.equals(this.dateContact, entity.dateContact) &&
                Objects.equals(this.noEtudiantNat, entity.noEtudiantNat) &&
                Objects.equals(this.anneePro, entity.anneePro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateContact, noEtudiantNat, anneePro);
    }

}