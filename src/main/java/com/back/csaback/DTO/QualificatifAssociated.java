package com.back.csaback.DTO;

import com.back.csaback.Models.Qualificatif;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QualificatifAssociated {
    private boolean associated;
    private Qualificatif qualificatif;

    public QualificatifAssociated(){
        this.associated = false;
    }
}
