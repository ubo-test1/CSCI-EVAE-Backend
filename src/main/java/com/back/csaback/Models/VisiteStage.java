package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "visite_stage")
public class VisiteStage {
    @EmbeddedId
    private VisiteStageId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "NO_ETUDIANT_NAT", referencedColumnName = "NO_ETUDIANT_NAT", nullable = false),
            @JoinColumn(name = "ANNEE_PRO", referencedColumnName = "ANNEE_PRO", nullable = false)
    })
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_CONTACT_ILI", nullable = false)
    private Employe noContactIli;

    @Column(name = "TYPE_CONTACT", nullable = false, length = 3)
    private String typeContact;

    @Column(name = "DATE_PREVISIONNELLE", nullable = false)
    private Instant datePrevisionnelle;

    @Column(name = "HEURE_PREVISIONNELLE", length = 20)
    private String heurePrevisionnelle;

    @Column(name = "DATE_EFFECTIVE")
    private Instant dateEffective;

    @Column(name = "HEURE_EFFECTIVE", length = 20)
    private String heureEffective;

    @Column(name = "DUREE", length = 20)
    private String duree;

    @Lob
    @Column(name = "COMMENTAIRE_TUTEUR_UBO")
    private String commentaireTuteurUbo;

}