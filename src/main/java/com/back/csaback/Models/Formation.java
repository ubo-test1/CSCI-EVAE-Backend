package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "FORMATION")
public class Formation {
    @Id
    @Size(max = 8)
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;

    @Size(max = 3)
    @NotNull
    @Column(name = "DIPLOME", nullable = false, length = 3)
    private String diplome;

    @NotNull
    @Column(name = "N0_ANNEE", nullable = false)
    private Boolean n0Annee = false;

    @Size(max = 64)
    @NotNull
    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    private String nomFormation;

    @NotNull
    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    private char doubleDiplome = 'O';

    @Column(name = "DEBUT_ACCREDITATION")
    private LocalDate debutAccreditation;

    @Column(name = "FIN_ACCREDITATION")
    private LocalDate finAccreditation;

}