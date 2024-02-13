package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @Column(name = "NO_CONTACT_ILI", nullable = false)
    private Double id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENTREPRISE", nullable = false)
    private Entreprise noEntreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ETUDIANT_NAT")
    private Diplome noEtudiantNat;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Column(name = "MAIL_PRO", length = 100)
    private String mailPro;

    @Column(name = "TEL_PRO", length = 20)
    private String telPro;

    @Column(name = "FONCTION", length = 5)
    private String fonction;

}