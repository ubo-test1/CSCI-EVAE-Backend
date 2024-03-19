package com.back.csaback.DTO;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.ReponseEvaluation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaEtudiantReponduDTO {
   public EvaEtudiantReponduDTO(Boolean repondu, Evaluation evaluation) {
      Repondu = repondu;
      this.evaluation = evaluation;
   }
   Boolean Repondu ;
   Evaluation evaluation;
   ReponseEvaluation reponseEvaluation;
}
