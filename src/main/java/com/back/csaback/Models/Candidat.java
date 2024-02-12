package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "candidat", schema = "csa_db")
public class Candidat {
    @Id
    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ANNEE_PRO", nullable = false)
    private Promotion anneePro;

    @Column(name = "SEXE", nullable = false)
    private Character sexe;

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

    @Column(name = "TEL_FIXE", length = 20)
    private String telFixe;

    @Column(name = "TEL_PORT", length = 20)
    private String telPort;

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

    @Column(name = "SELECTION_ORIGINE", nullable = false, length = 2)
    private String selectionOrigine;

    @Column(name = "SELECTION_COURANTE", nullable = false, length = 2)
    private String selectionCourante;

    @Column(name = "REPONSE_ILI", nullable = false)
    private Character reponseIli;

    @Column(name = "DATE_REPONSE_ILI")
    private Instant dateReponseIli;

    @Column(name = "REPONSE_CAN", length = 3)
    private String reponseCan;

    @Column(name = "DATE_REPONSE_CAN")
    private Instant dateReponseCan;

    @Column(name = "NO_ORDRE")
    private Double noOrdre;

}