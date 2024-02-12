package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "promotion", schema = "csa_db")
public class Promotion {
    @Id
    @Column(name = "ANNEE_PRO", nullable = false, length = 10)
    private String anneePro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_FORMATION")
    private Formation codeFormation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @Column(name = "SIGLE_PRO", nullable = false, length = 5)
    private String siglePro;

    @Column(name = "NB_ETU_SOUHAITE", nullable = false)
    private Short nbEtuSouhaite;

    @Column(name = "ETAT_PRESELECTION", nullable = false, length = 3)
    private String etatPreselection;

    @Column(name = "DATE_RENTREE")
    private Instant dateRentree;

    @Column(name = "LIEU_RENTREE")
    private String lieuRentree;

    @Column(name = "DATE_REPONSE_LP")
    private Instant dateReponseLp;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @Column(name = "DATE_REPONSE_LALP")
    private Instant dateReponseLalp;

    @Column(name = "PROCESSUS_STAGE", length = 5)
    private String processusStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_EVALUATION")
    private StructureEvaluation noEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_BAREME")
    private NotationStage noBareme;

}