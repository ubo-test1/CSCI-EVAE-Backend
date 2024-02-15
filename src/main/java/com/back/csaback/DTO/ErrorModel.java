package com.back.csaback.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Modèle de données pour représenter les erreurs retournées par l'API.
 * Cet objet contient des informations sur le statut de l'erreur, le message d'erreur et une suggestion pour résoudre l'erreur.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2023
 * */
@Getter
@Setter

public class ErrorModel {


    public ErrorModel(Long status, String message, String suggestion) {
        this.status = status;
        this.message = message;
        this.suggestion = suggestion;
    }

    private Long status;
    private String message;
    private String suggestion;
}
