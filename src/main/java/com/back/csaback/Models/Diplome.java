package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diplome", schema = "csa_db")
public class Diplome {
    @Id
    @Column(name = "NO_ETUDIANT_NAT", nullable = false, length = 50)
    private String noEtudiantNat;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ETUDIANT_NAT", nullable = false)
    private Etudiant etudiant;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Column(name = "AUTORISATION_ANNUAIRE", nullable = false)
    private Character autorisationAnnuaire;

    @Column(name = "TYPE_EMPLOI", length = 3)
    private String typeEmploi;

    @Column(name = "MAIL_PRO", length = 100)
    private String mailPro;

    @Column(name = "TEL_PRO", length = 20)
    private String telPro;

    @Column(name = "ADRESSE", length = 100)
    private String adresse;

    @Column(name = "CP", length = 10)
    private String cp;

    @Column(name = "VILLE", length = 50)
    private String ville;

    @Column(name = "EMAIL_PERSO", length = 100)
    private String emailPerso;

    @Column(name = "TEL_PERSO", length = 20)
    private String telPerso;

    @Column(name = "PORTABLE", length = 20)
    private String portable;

}