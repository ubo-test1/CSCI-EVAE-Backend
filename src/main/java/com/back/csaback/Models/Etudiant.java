package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @Column(name = "CODE_COM", length = 10)
    private String codeCom;

    @Column(name = "NO_ETUDIANT_UBO", length = 20)
    private String noEtudiantUbo;

    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Column(name = "DATE_NAISSANCE", nullable = false)
    private Instant dateNaissance;

    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    @Column(name = "SITUATION", nullable = false, length = 3)
    private String situation;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "TEL_PORT", length = 20)
    private String telPort;

    @Column(name = "TEL_FIXE", length = 20)
    private String telFixe;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTU_ADRESSE")
    private String actuAdresse;

    @Column(name = "ACTU_CP", length = 10)
    private String actuCp;

    @Column(name = "ACTU_VILLE")
    private String actuVille;

    @Column(name = "ACTU_PAYS")
    private String actuPays;

    @Column(name = "PERM_ADRESSE", nullable = false)
    private String permAdresse;

    @Column(name = "PERM_CP", nullable = false, length = 10)
    private String permCp;

    @Column(name = "PERM_VILLE", nullable = false)
    private String permVille;

    @Column(name = "PERM_PAYS", nullable = false)
    private String permPays;

    @Column(name = "DERNIER_DIPLOME", nullable = false)
    private String dernierDiplome;

    @Column(name = "UNIVERSITE", nullable = false)
    private String universite;

    @Column(name = "SIGLE_ETU", nullable = false, length = 3)
    private String sigleEtu;

    @Column(name = "COMPTE_CRI", nullable = false, length = 10)
    private String compteCri;

    @Column(name = "UBO_EMAIL")
    private String uboEmail;

    @Column(name = "GRPE_ANGLAIS")
    private Byte grpeAnglais;

    @Column(name = "ABANDON_MOTIF")
    private String abandonMotif;

    @Column(name = "ABANDON_DATE")
    private Instant abandonDate;

    @Column(name = "EST_DIPLOME")
    private Character estDiplome;

}