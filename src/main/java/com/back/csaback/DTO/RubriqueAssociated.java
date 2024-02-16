package com.back.csaback.DTO;

import com.back.csaback.Models.Rubrique;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RubriqueAssociated {
    public RubriqueAssociated(Rubrique rubrique, Boolean associated) {
        this.rubrique = rubrique;
        this.associated = associated;
    }

    Rubrique rubrique;
    Boolean associated;
}
