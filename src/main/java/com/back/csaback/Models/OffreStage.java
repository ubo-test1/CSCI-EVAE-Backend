package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "offre_stage")
public class OffreStage {
    @Id
    @Column(name = "NO_OFFRE", nullable = false)
    private Double id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENTREPRISE", nullable = false)
    private Entreprise noEntreprise;

    @Column(name = "NO_OFFRE_ENTREPRISE", length = 20)
    private String noOffreEntreprise;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @Column(name = "INTITULE", nullable = false, length = 100)
    private String intitule;

    @Column(name = "SUJET", nullable = false)
    private String sujet;

    @Column(name = "DATE_CREATION", nullable = false)
    private Instant dateCreation;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @Column(name = "DUREE", nullable = false, length = 30)
    private String duree;

    @Column(name = "ETAT_OFFRE", nullable = false, length = 3)
    private String etatOffre;

    @Column(name = "LIEU", nullable = false, length = 80)
    private String lieu;

    @Column(name = "NIVEAU_REQUIS", nullable = false, length = 5)
    private String niveauRequis;

    @Column(name = "PERIODE", nullable = false, length = 50)
    private String periode;

    @Column(name = "NOM_RESPONSABLE", nullable = false, length = 50)
    private String nomResponsable;

    @Column(name = "PRENOM_RESPONSABLE", nullable = false, length = 50)
    private String prenomResponsable;

    @Column(name = "TEL_RESPONSABLE", length = 20)
    private String telResponsable;

    @Column(name = "MAIL_RESPONSABLE", length = 100)
    private String mailResponsable;

    @Column(name = "REMUNERATION", length = 20)
    private String remuneration;

}