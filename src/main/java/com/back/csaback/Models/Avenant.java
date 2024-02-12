package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "avenant", schema = "csa_db")
public class Avenant {
    @Id
    @Column(name = "NO_AVENANT", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ANNEE_PRO", referencedColumnName = "ANNEE_PRO", nullable = false),
            @JoinColumn(name = "NO_ETUDIANT_NAT", referencedColumnName = "NO_ETUDIANT_NAT", nullable = false)
    })
    private Stage stage;

    @Column(name = "ETAT_AVENANT", nullable = false)
    private String etatAvenant;

    @Column(name = "NUM_ARTICLE", nullable = false)
    private Integer numArticle;

    @Column(name = "DATE_SIGNATURE_AVE")
    private Instant dateSignatureAve;

    @Column(name = "MODIF_APPORTEE", nullable = false)
    private String modifApportee;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

}