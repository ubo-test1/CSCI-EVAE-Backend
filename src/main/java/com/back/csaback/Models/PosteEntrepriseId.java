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
public class PosteEntrepriseId implements Serializable {
    private static final long serialVersionUID = -851794937653556303L;
    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @Column(name = "NO_ENTREPRISE", nullable = false)
    private Double noEntreprise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PosteEntrepriseId entity = (PosteEntrepriseId) o;
        return Objects.equals(this.noEntreprise, entity.noEntreprise) &&
                Objects.equals(this.noEtudiantNat, entity.noEtudiantNat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEntreprise, noEtudiantNat);
    }

}