package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "poste_entreprise")
public class PosteEntreprise {
    @EmbeddedId
    private PosteEntrepriseId id;

    @MapsId("noEtudiantNat")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ETUDIANT_NAT", nullable = false)
    private Diplome noEtudiantNat;

    @MapsId("noEntreprise")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENTREPRISE", nullable = false)
    private Entreprise noEntreprise;

    @Column(name = "FONCTION", nullable = false, length = 5)
    private String fonction;

    @Column(name = "DATE_EMBAUCHE", nullable = false)
    private Instant dateEmbauche;

    @Column(name = "MOYEN_OBTENTION", nullable = false, length = 3)
    private String moyenObtention;

    @Column(name = "SERVICE", length = 5)
    private String service;

    @Column(name = "BRUT_ANNUEL")
    private Integer brutAnnuel;

}