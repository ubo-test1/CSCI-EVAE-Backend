package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "soutenance", schema = "csa_db")
public class Soutenance {
    @EmbeddedId
    private SoutenanceId id;

    @MapsId("anneePro")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT_RESPONSABLE", nullable = false)
    private Enseignant noEnseignantResponsable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT_ASSESSEUR", nullable = false)
    private Enseignant noEnseignantAssesseur;

    @Column(name = "DATE_SOUTENANCE", nullable = false)
    private Instant dateSoutenance;

    @Column(name = "PLAGE_HORAIRE", nullable = false, length = 50)
    private String plageHoraire;

    @Column(name = "SALLE", nullable = false, length = 12)
    private String salle;

}