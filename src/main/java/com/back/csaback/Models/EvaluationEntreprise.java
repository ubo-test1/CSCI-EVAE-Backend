package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "evaluation_entreprise")
public class EvaluationEntreprise {
    @EmbeddedId
    private EvaluationEntrepriseId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ANNEE_PRO", referencedColumnName = "NO_ETUDIANT_NAT", nullable = false),
            @JoinColumn(name = "NO_ETUDIANT_NAT", referencedColumnName = "ANNEE_PRO", nullable = false)
    })
    private Stage stage;

    @Column(name = "DATE_MAJ", nullable = false)
    private Instant dateMaj;

    @Column(name = "ETAT", nullable = false, length = 3)
    private String etat;

}