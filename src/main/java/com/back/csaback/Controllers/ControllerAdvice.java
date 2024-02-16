package com.back.csaback.Controllers;

import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import com.back.csaback.DTO.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ErrorQualificatifAssociated.class)
    public ResponseEntity<ErrorModel> associatedQuestionExceptionHandler(ErrorQualificatifAssociated exception){
        return ResponseEntity.status(422).body(new ErrorModel(422L, exception.getMessage(), "Try another quetion"));
    }
}
