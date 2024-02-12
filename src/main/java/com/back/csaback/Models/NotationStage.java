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
@Table(name = "notation_stage", schema = "csa_db")
public class NotationStage {
    @Id
    @Column(name = "NO_BAREME", nullable = false)
    private Double id;

    @Column(name = "COEFF_ENT", nullable = false)
    private Double coeffEnt;

    @Column(name = "COEFF_RAPPORT", nullable = false)
    private Double coeffRapport;

    @Column(name = "COEFF_SOUTENANCE", nullable = false)
    private Double coeffSoutenance;

    @Column(name = "DATE_CREATION", nullable = false)
    private Instant dateCreation;

    @Column(name = "ETAT_BAREME", nullable = false, length = 3)
    private String etatBareme;

}