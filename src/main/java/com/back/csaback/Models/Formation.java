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
@Table(name = "formation", schema = "csa_db")
public class Formation {
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;

    @Column(name = "DIPLOME", nullable = false, length = 3)
    private String diplome;

    @Column(name = "N0_ANNEE", nullable = false)
    private Byte n0Annee;

    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    private String nomFormation;

    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    private Character doubleDiplome;

    @Column(name = "DEBUT_HABILITATION")
    private Instant debutHabilitation;

    @Column(name = "FIN_HABILITATION")
    private Instant finHabilitation;

}