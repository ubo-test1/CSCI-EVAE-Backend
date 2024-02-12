package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @Column(name = "NO_ENTREPRISE", nullable = false)
    private Double id;

    @Column(name = "REFERENCEE")
    private Character referencee;

    @Column(name = "DATE_REFERENCEMENT")
    private Instant dateReferencement;

    @Column(name = "LOGIN_CREA", length = 8)
    private String loginCrea;

    @Column(name = "DATE_CREA", nullable = false)
    private Instant dateCrea;

    @Column(name = "NOM", nullable = false, length = 100)
    private String nom;

    @Column(name = "SIEGE_SOCIAL", nullable = false, length = 100)
    private String siegeSocial;

    @Column(name = "DOMAINE_ACTIVITE", nullable = false, length = 5)
    private String domaineActivite;

    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Column(name = "CP", nullable = false, length = 10)
    private String cp;

    @Column(name = "VILLE", nullable = false, length = 100)
    private String ville;

    @Column(name = "PAYS", nullable = false, length = 100)
    private String pays;

    @Column(name = "TEL", nullable = false, length = 20)
    private String tel;

    @Column(name = "SITE_INTERNET", length = 150)
    private String siteInternet;

    @Column(name = "NOM_REPRESENTANT", length = 50)
    private String nomRepresentant;

    @Column(name = "PRENOM_REPRESENTANT", length = 50)
    private String prenomRepresentant;

    @Column(name = "LOGIN_MAJ", length = 8)
    private String loginMaj;

    @Column(name = "DATE_MAJ")
    private Instant dateMaj;

    @Column(name = "OFFRE_STAGE")
    private Character offreStage;

}