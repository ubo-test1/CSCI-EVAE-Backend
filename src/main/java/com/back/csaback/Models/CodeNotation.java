package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "code_notation", schema = "csa_db")
public class CodeNotation {
    @Id
    @Column(name = "CODN_ID", nullable = false)
    private Double id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_BAREME", nullable = false)
    private NotationStage noBareme;

    @Column(name = "CODE_NOTE", nullable = false, length = 3)
    private String codeNote;

    @Column(name = "DESIGNATION", nullable = false, length = 50)
    private String designation;

    @Column(name = "VALEUR_NOTE", nullable = false)
    private Double valeurNote;

    @Lob
    @Column(name = "DESCRIPTIF")
    private String descriptif;

}