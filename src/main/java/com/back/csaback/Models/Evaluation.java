package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @Column(name = "ID_EVALUATION", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false),
            @JoinColumn(name = "CODE_EC", referencedColumnName = "CODE_EC", nullable = false)
    })
    private ElementConstitutif elementConstitutif;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @Column(name = "NO_EVALUATION", nullable = false)
    private Byte noEvaluation;

    @Column(name = "ETAT", nullable = false, length = 3)
    private String etat;

    @Column(name = "PERIODE", length = 64)
    private String periode;

    @Column(name = "DEBUT_REPONSE", nullable = false)
    private Instant debutReponse;

    @Column(name = "FIN_REPONSE", nullable = false)
    private Instant finReponse;

}