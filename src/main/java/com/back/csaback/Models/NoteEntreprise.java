package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "note_entreprise")
public class NoteEntreprise {
    @EmbeddedId
    private NoteEntrepriseId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "NO_ETUDIANT_NAT", referencedColumnName = "NO_ETUDIANT_NAT", nullable = false),
            @JoinColumn(name = "ANNEE_PRO", referencedColumnName = "ANNEE_PRO", nullable = false)
    })
    private EvaluationEntreprise evaluationEntreprise;

    @MapsId("criteId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CRITE_ID", nullable = false)
    private CritereEntreprise crite;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CODN_ID", nullable = false)
    private CodeNotation codn;

    @Lob
    @Column(name = "COMMENTAIRE")
    private String commentaire;

}