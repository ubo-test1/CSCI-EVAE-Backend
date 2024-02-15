package com.back.csaback.Controllers;


import com.back.csaback.DTO.ErrorModel;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Conseiller de contrôleur global pour la gestion des exceptions dans l'ensemble de l'application.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2023
 */

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ErrorQuestionAssociated.class)
    public ResponseEntity<ErrorModel> associatedQuestionExceptionHandler(ErrorQuestionAssociated exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "Try another quetion"));
    }
}
