package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question", schema = "csa_db")
public class Question {
    @Id
    @Column(name = "ID_QUESTION", nullable = false)
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_QUALIFICATIF", nullable = false)
    private Qualificatif idQualificatif;

    @Column(name = "`INTITULֹ`", nullable = false, length = 64)
    private String intitulֹ;

}