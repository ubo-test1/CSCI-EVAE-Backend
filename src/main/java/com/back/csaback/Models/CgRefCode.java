package com.back.csaback.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CG_REF_CODES")
public class CgRefCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cg_generator")
    @SequenceGenerator(name="cg_generator", sequenceName = "CGRC_SEQ", allocationSize=1)
    @Column(name = "ID_CGRC", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "RV_DOMAIN", nullable = false, length = 100)
    private String rvDomain;

    @Size(max = 240)
    @NotNull
    @Column(name = "RV_LOW_VALUE", nullable = false, length = 240)
    private String rvLowValue;

    @Size(max = 240)
    @Column(name = "RV_HIGH_VALUE", length = 240)
    private String rvHighValue;

    @Size(max = 240)
    @Column(name = "RV_ABBREVIATION", length = 240)
    private String rvAbbreviation;

    @Size(max = 240)
    @Column(name = "RV_MEANING", length = 240)
    private String rvMeaning;

}