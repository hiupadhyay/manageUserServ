package com.user.manage.responses;

import lombok.Data;

@Data
public class RegistrationResponse {

    public RegistrationResponse(String message) {
        this.message = message;
    }

    private String message;
}
