package com.back.csaback.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enseignant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Enseignant {
    @Id
    @Column(name = "NO_ENSEIGNANT", nullable = false)
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;

    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Column(name = "CP", nullable = false, length = 10)
    private String cp;

    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Column(name = "PAYS", nullable = false)
    private String pays;

    @Column(name = "TEL_PORT", length = 20)
    private String telPort;

    @Column(name = "ENC_PERSO_TEL", length = 20)
    private String encPersoTel;

    @Column(name = "ENC_UBO_TEL", length = 20)
    private String encUboTel;

    @Column(name = "ENC_PERSO_EMAIL")
    private String encPersoEmail;

    @Column(name = "ENC_UBO_EMAIL")
    private String encUboEmail;

    @Column(name = "INT_NO_INSEE", length = 50)
    private String intNoInsee;

    @Column(name = "INT_SOC_NOM", length = 50)
    private String intSocNom;

    @Column(name = "INT_SOC_ADRESSE")
    private String intSocAdresse;

    @Column(name = "INT_SOC_CP", length = 10)
    private String intSocCp;

    @Column(name = "INT_SOC_VILLE")
    private String intSocVille;

    @Column(name = "INT_SOC_PAYS")
    private String intSocPays;

    @Column(name = "INT_FONCTION", length = 50)
    private String intFonction;

    @Column(name = "INT_PROF_EMAIL")
    private String intProfEmail;

    @Column(name = "INT_PROF_TEL", length = 20)
    private String intProfTel;

}