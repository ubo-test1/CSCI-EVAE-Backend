package com.back.csaback.Models;

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
@Table(name = "AUTHENTIFICATION")
public class Authentification {
    @Id
    @Column(name = "ID_CONNECTION", nullable = false)
    private Long id;

    @Size(max = 5)
    @NotNull
    @Column(name = "ROLE", nullable = false, length = 5)
    private String role;

    @Size(max = 64)
    @NotNull
    @Column(name = "LOGIN_CONNECTION", nullable = false, length = 64)
    private String loginConnection;

    @Size(max = 240)
    @Column(name = "PSEUDO_CONNECTION", length = 240)
    private String pseudoConnection;

    @Size(max = 32)
    @Column(name = "MOT_PASSE", length = 32)
    private String motPasse;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NO_ETUDIANT")
    private Etudiant noEtudiant;

    @Override
    public String toString() {
        return "Authentification{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", loginConnection='" + loginConnection + '\'' +
                ", pseudoConnection='" + pseudoConnection + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", noEnseignant=" + noEnseignant +
                ", noEtudiant=" + noEtudiant +
                '}';
    }
}