//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(
        name = "qualificatif"
)
public class Qualificatif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_QUALIFICATIF", nullable = false)
    private Long idQualificatif;
    @Column(name = "MAXIMAL")
    private String maximal;
    @Column(
            name = "MINIMAL"
    )
    private String minimal;

    public Qualificatif() {
    }



    public Qualificatif(Long idQualificatif, String maximal, String minimal) {
        this.idQualificatif = idQualificatif;
        this.maximal = maximal;
        this.minimal = minimal;
    }
}
