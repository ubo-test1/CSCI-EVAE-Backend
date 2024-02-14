package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rubrique")
public class Rubrique {

    @Id
    @Column(name = "ID_RUBRIQUE", nullable = false)
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;

    @Column(name = "DESIGNATION", nullable = false, length = 32)
    private String designation;

    @Column(name = "ORDRE")
    private Double ordre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

}