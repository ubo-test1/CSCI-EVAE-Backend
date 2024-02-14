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
import java.io.Serializable;

@Entity
@Table(
        name = "qualificatif"
)
public class Qualificatif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "ID_QUALIFICATIF"
    )
    private Long idQualificatif;
    @Column(
            name = "MAXIMAL"
    )
    private String maximal;
    @Column(
            name = "MINIMAL"
    )
    private String minimal;

    public Qualificatif() {
    }

    public Long getIdQualificatif() {
        return this.idQualificatif;
    }

    public void setIdQualificatif(Long idQualificatif) {
        this.idQualificatif = idQualificatif;
    }

    public String getMaximal() {
        return this.maximal;
    }

    public void setMaximal(String maximal) {
        this.maximal = maximal;
    }

    public String getMinimal() {
        return this.minimal;
    }

    public void setMinimal(String minimal) {
        this.minimal = minimal;
    }

    public Qualificatif(Long idQualificatif, String maximal, String minimal) {
        this.idQualificatif = idQualificatif;
        this.maximal = maximal;
        this.minimal = minimal;
    }
}
