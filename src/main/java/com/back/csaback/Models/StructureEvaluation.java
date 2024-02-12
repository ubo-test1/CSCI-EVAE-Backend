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
@Table(name = "structure_evaluation", schema = "csa_db")
public class StructureEvaluation {
    @Id
    @Column(name = "NO_EVALUATION", nullable = false)
    private Double id;

    @Column(name = "DATE_CREATION", nullable = false)
    private Instant dateCreation;

    @Column(name = "ETAT", nullable = false, length = 3)
    private String etat;

}