package com.back.csaback.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cg_ref_codes")
public class CgRefCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RV_DOMAIN", nullable = false, length = 100)
    private String rvDomain;

    @Column(name = "RV_LOW_VALUE", nullable = false, length = 240)
    private String rvLowValue;

    @Column(name = "RV_HIGH_VALUE", length = 240)
    private String rvHighValue;

    @Column(name = "RV_ABBREVIATION", length = 240)
    private String rvAbbreviation;

    @Column(name = "RV_MEANING", length = 240)
    private String rvMeaning;

}