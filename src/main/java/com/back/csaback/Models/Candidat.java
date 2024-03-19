package com.back.csaback.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CANDIDAT")
public class Candidat {
    @Id
    @Size(max = 50)
    @Column(name = "NO_CANDIDAT", nullable = false, length = 50)
    private String noCandidat;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "ANNEE_UNIVERSITAIRE", nullable = false),
            @JoinColumn(name = "ANNEE_UNIVERSITAIRE", referencedColumnName = "CODE_FORMATION", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Promotion promotion;

    @Size(max = 50)
    @NotNull
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Size(max = 50)
    @NotNull
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Size(max = 1)
    @NotNull
    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @NotNull
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDate dateNaissance;

    @Size(max = 255)
    @NotNull
    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    @Size(max = 50)
    @NotNull
    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Size(max = 20)
    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Size(max = 20)
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Size(max = 255)
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Size(max = 10)
    @Column(name = "CODE_POSTAL", length = 10)
    private String codePostal;

    @Size(max = 255)
    @NotNull
    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Size(max = 5)
    @NotNull
    @Column(name = "PAYS_ORIGINE", nullable = false, length = 5)
    private String paysOrigine;

    @Size(max = 6)
    @NotNull
    @Column(name = "UNIVERSITE_ORIGINE", nullable = false, length = 6)
    private String universiteOrigine;

    @Size(max = 6)
    @Column(name = "LISTE_SELECTION", length = 6)
    private String listeSelection;

    @Column(name = "SELECTION_NO_ORDRE")
    private Long selectionNoOrdre;

    @Column(name = "CONFIRMATION_CANDIDAT")
    private char confirmationCandidat;

    @Column(name = "DATE_REPONSE_CANDIDAT")
    private LocalDate dateReponseCandidat;

}