package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "element_constitutif")
public class ElementConstitutif {
    @EmbeddedId
    private ElementConstitutifId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("codeUe")
    @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false)
    private UniteEnseignement uniteEnseignement;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;

    @Column(name = "DESCRIPTION", nullable = false, length = 240)
    private String description;

    @Column(name = "NBH_CM")
    private Byte nbhCm;

    @Column(name = "NBH_TD")
    private Byte nbhTd;

    @Column(name = "NBH_TP")
    private Byte nbhTp;

}