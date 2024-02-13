package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "entreprise_jn")
public class EntrepriseJn {

    @Column(name = "JN_OPERATION", nullable = false, length = 3)
    private String jnOperation;

    @Column(name = "JN_ORACLE_USER", nullable = false, length = 30)
    private String jnOracleUser;

    @Column(name = "JN_DATETIME", nullable = false)
    private Instant jnDatetime;

    @Column(name = "JN_NOTES", length = 240)
    private String jnNotes;

    @Column(name = "JN_APPLN", length = 35)
    private String jnAppln;

    @Column(name = "JN_SESSION", precision = 38)
    private BigDecimal jnSession;

    @Column(name = "NO_ENTREPRISE", nullable = false)
    private Double noEntreprise;

    @Column(name = "REFERENCEE")
    private Character referencee;

    @Column(name = "DATE_REFERENCEMENT")
    private Instant dateReferencement;

    @Column(name = "LOGIN_CREA", length = 8)
    private String loginCrea;

    @Column(name = "DATE_CREA")
    private Instant dateCrea;

    @Column(name = "NOM", length = 100)
    private String nom;

    @Column(name = "SIEGE_SOCIAL", length = 100)
    private String siegeSocial;

    @Column(name = "DOMAINE_ACTIVITE", length = 5)
    private String domaineActivite;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "CP", length = 10)
    private String cp;

    @Column(name = "VILLE", length = 100)
    private String ville;

    @Column(name = "PAYS", length = 100)
    private String pays;

    @Column(name = "TEL", length = 20)
    private String tel;

    @Column(name = "SITE_INTERNET", length = 150)
    private String siteInternet;

    @Column(name = "NOM_REPRESENTANT", length = 50)
    private String nomRepresentant;

    @Column(name = "PRENOM_REPRESENTANT", length = 50)
    private String prenomRepresentant;

    @Id
    @Column(name = "LOGIN_MAJ", length = 8)
    private String loginMaj;

    @Column(name = "DATE_MAJ")
    private Instant dateMaj;

    @Column(name = "OFFRE_STAGE")
    private Character offreStage;

}