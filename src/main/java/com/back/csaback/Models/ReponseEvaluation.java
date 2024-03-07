package com.back.csaback.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "REPONSE_EVALUATION")
public class ReponseEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rev_generator")
    @SequenceGenerator(name="rev_generator", sequenceName = "REV_SEQ", allocationSize=1)
    @Column(name = "ID_REPONSE_EVALUATION", nullable = false)
    private Integer id;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_EVALUATION", nullable = false)
    private Evaluation idEvaluation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NO_ETUDIANT")
    private Etudiant noEtudiant;

    @Size(max = 512)
    @Column(name = "COMMENTAIRE", length = 512)
    private String commentaire;

    @Size(max = 32)
    @Column(name = "NOM", length = 32)
    private String nom;

    @Size(max = 32)
    @Column(name = "PRENOM", length = 32)
    private String prenom;

}