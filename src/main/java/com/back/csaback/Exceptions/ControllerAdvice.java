package com.back.csaback.Exceptions;


import com.back.csaback.DTO.ErrorModel;
import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Conseiller de contrôleur global pour la gestion des exceptions dans l'ensemble de l'application.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2024
 */

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ErrorQuestionAssociated.class)
    public ResponseEntity<ErrorModel> associatedQuestionExceptionHandler(ErrorQuestionAssociated exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "Try another quetion"));
    }
    @ExceptionHandler(ErrorQuestionAlreadyExist.class)
    public ResponseEntity<ErrorModel> QuestionAlreadyExistExceptionHandler(ErrorQuestionAlreadyExist exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "Enter another quetion"));
    }
    @ExceptionHandler(ErrorQualificatifAssociated.class)
    public ResponseEntity<ErrorModel> QualificatifAssociatedExceptionHandler(ErrorQualificatifAssociated exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "Enter another couple qualificatif"));
    }

    @ExceptionHandler(QualificatifExistException.class)
    public ResponseEntity<ErrorModel> QualificatifExistExceptionHandler(QualificatifExistException exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "try another couple qualificatif"));
    }
    @ExceptionHandler(ErrorRubriqueEvaluationAlreadyExist.class)
    public ResponseEntity<ErrorModel> QualificatifExistExceptionHandler(ErrorRubriqueEvaluationAlreadyExist exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "try another rubrique"));
    }
    @ExceptionHandler(ErrorEvaluationNoOuverte.class)
    public ResponseEntity<ErrorModel> QualificatifExistExceptionHandler(ErrorEvaluationNoOuverte exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "il faut choisir une autre evaluation avec une etat en cours oub changer l'etat de l'evaluation actuelle"));
    }

}
