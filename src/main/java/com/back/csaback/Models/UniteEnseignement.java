package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "unite_enseignement")
public class UniteEnseignement {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    private Formation codeFormation;

    @Id
    @Column(name = "CODE_UE", nullable = false, length = 8)
    private String codeUe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;

    @Column(name = "SEMESTRE", nullable = false, length = 3)
    private String semestre;

    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "NBH_CM", precision = 38)
    private BigDecimal nbhCm;

    @Column(name = "NBH_TD")
    private Byte nbhTd;

    @Column(name = "NBH_TP")
    private Byte nbhTp;

}