package com.back.csaback.Exceptions;


/**
 * Exception utilisée pour signaler des erreurs liées à des questions déjà existes dans la bdd .
 * Exception levée lorsqu'une tentative est faite de créer une question qui existe déjà.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2024
 */
public class ErrorQuestionAlreadyExist extends RuntimeException{
    /**
     * Construit une nouvelle instance de ErrorQuestionAlreadyExist avec le message détaillé spécifié.
     *
     * @param msg le message détaillé.
     */
    public ErrorQuestionAlreadyExist(String msg){super(msg);}
}
