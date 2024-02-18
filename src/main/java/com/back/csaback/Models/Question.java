package com.back.csaback.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
<<<<<<< HEAD
=======
import jakarta.validation.constraints.Size;
>>>>>>> 5e8e0b197ba38f2d42bfe30f5d9bbefce4fb5028
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
<<<<<<< HEAD
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
=======
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ques_generator")
    @SequenceGenerator(name="ques_generator", sequenceName = "QUE_SEQ", allocationSize=1)
    @Column(name = "ID_QUESTION", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_QUALIFICATIF", nullable = false)
    private Qualificatif idQualificatif;

    @Size(max = 64)
    @NotNull
>>>>>>> 5e8e0b197ba38f2d42bfe30f5d9bbefce4fb5028
    @Column(name = "INTITULE", nullable = false, length = 64)
    private String intitule;


}