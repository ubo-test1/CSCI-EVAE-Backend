package com.back.csaback.Exceptions;



/**
 * Exception utilisée pour signaler des erreurs liées à des rubriques  déjà associées à une evaluation.
 * Cette exception est levée lorsqu'une opération impliquant une rubrique est interdite en raison de son existance dasns une evaluation.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 11/03/2024
 */
public class ErrorRubriqueEvaluationAlreadyExist extends RuntimeException {
    public ErrorRubriqueEvaluationAlreadyExist(String msg){super(msg);}

}
