package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_stage")
public class ContactStage {
    @EmbeddedId
    private ContactStageId id;

    @MapsId("noEtudiantNat")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ETUDIANT_NAT", nullable = false)
    private Etudiant noEtudiantNat;

    @Column(name = "TYPE_CONTACT", nullable = false, length = 3)
    private String typeContact;

    @Column(name = "INTERLOCUTEUR", length = 3)
    private String interlocuteur;

    @Column(name = "OBJET", nullable = false)
    private String objet;

    @Column(name = "DUREE", length = 20)
    private String duree;

    @Lob
    @Column(name = "RESUME")
    private String resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_CONTACT_ILI")
    private Employe noContactIli;

    @Column(name = "HEURE_CONTACT", length = 20)
    private String heureContact;

}