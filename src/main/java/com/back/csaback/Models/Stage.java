package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "stage")
public class Stage {
    @EmbeddedId
    private StageId id;

    @MapsId("anneePro")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @MapsId("noEtudiantNat")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ETUDIANT_NAT", nullable = false)
    private Etudiant noEtudiantNat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENTREPRISE", nullable = false)
    private Entreprise noEntreprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NO_OFFRE")
    private OffreStage noOffre;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_CONTACT_ILI", nullable = false)
    private Employe noContactIli;

    @Column(name = "INTITULE", nullable = false, length = 200)
    private String intitule;

    @Column(name = "SUJET", nullable = false, length = 200)
    private String sujet;

    @Column(name = "DATE_DEB", nullable = false)
    private Instant dateDeb;

    @Column(name = "DATE_FIN", nullable = false)
    private Instant dateFin;

    @Column(name = "LIEU", nullable = false, length = 50)
    private String lieu;

    @Column(name = "ETAT_STAGE", nullable = false, length = 3)
    private String etatStage;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @Column(name = "ETAT_CONVENTION", length = 3)
    private String etatConvention;

    @Column(name = "DATE_SIGNATURE_CONV")
    private Instant dateSignatureConv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @Column(name = "COMMENTAIRE_TUTEUR")
    private String commentaireTuteur;

    @Column(name = "DATE_RECEPTION_RAPPORT")
    private Instant dateReceptionRapport;

    @Column(name = "NOTE_ENTREPRISE")
    private Double noteEntreprise;

    @Column(name = "NOTE_RAPPORT")
    private Double noteRapport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "ANNEE_PRO", referencedColumnName = "ANNEE_PRO"),
            @JoinColumn(name = "NO_SESSION", referencedColumnName = "NO_SESSION")
    })
    private Soutenance soutenance;

    @Column(name = "NOTE_SOUTENANCE")
    private Double noteSoutenance;

}