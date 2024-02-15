package com.back.csaback.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
   // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="question_sequence")
   //  @SequenceGenerator(name="question_sequence", sequenceName="QUESTION_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_QUESTION", nullable = false)
    private Long id;
    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;
    @NotNull(message = "vous devez choisir un couple qualificatif")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_QUALIFICATIF", nullable = false)
    private Qualificatif idQualificatif;
    @NotNull(message = "vous devez fournir un intitule")
    @Column(name = "INTITULE", nullable = false, length = 64)
    private String intitule;


}