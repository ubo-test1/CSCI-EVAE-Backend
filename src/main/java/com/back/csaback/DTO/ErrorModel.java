package com.back.csaback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Error {


    public Error(Long status, String message, String suggestion) {
        this.status = status;
        this.message = message;
        this.suggestion = suggestion;
    }

    private Long status;
    private String message;
    private String suggestion;
}
