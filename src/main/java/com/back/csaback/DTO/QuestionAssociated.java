package com.back.csaback.DTO;

import com.back.csaback.Models.Question;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) représentant une question et son statut d'association.
 * Cette classe contient une question et un indicateur indiquant si la question est associée à une rubrique ou non.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2023
 */

@Getter
@Setter
public class QuestionAssociated {
    boolean associated;
    Question question;
}
