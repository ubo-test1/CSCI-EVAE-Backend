package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "critere_entreprise")
public class CritereEntreprise {
    @Id
    @Column(name = "CRITE_ID", nullable = false)
    private Double id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_EVALUATION", nullable = false)
    private StructureEvaluation noEvaluation;

    @Column(name = "ORDRE", nullable = false)
    private Double ordre;

    @Column(name = "DESIGNATION", nullable = false, length = 200)
    private String designation;

    @Column(name = "POIDS", nullable = false)
    private Double poids;

    @Lob
    @Column(name = "DESCRIPTIF")
    private String descriptif;

}