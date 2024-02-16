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
public class UniteEnseignementId implements Serializable {
    private static final long serialVersionUID = -7367458230415783068L;
    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;

    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_UE", nullable = false, length = 8)
    private String codeUe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UniteEnseignementId entity = (UniteEnseignementId) o;
        return Objects.equals(this.codeUe, entity.codeUe) &&
                Objects.equals(this.codeFormation, entity.codeFormation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeUe, codeFormation);
    }

}