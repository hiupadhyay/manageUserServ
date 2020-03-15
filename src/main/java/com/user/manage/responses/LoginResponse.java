package com.user.manage.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String app_id;
    private String auth_key;

    public LoginResponse(String message,String app_id, String auth_key) {
        this.message = message;
        this.app_id = app_id;
        this.auth_key = auth_key;
    }
}
