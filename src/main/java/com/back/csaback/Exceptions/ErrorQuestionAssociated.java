package com.back.csaback.Exceptions;


/**
 * Exception utilisée pour signaler des erreurs liées à des questions déjà associées à une rubrique.
 * Cette exception est levée lorsqu'une opération impliquant une question est interdite en raison de son association à une rubrique.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2023
 */

public class ErrorQuestionAssociated extends RuntimeException{

    public ErrorQuestionAssociated(String msg){
        super(msg);
    }
}
