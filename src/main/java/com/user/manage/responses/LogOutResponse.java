package com.user.manage.responses;

import lombok.Data;

@Data
public class LogOutResponse {

    private String app_id;
    private String message;

    public LogOutResponse(String message, String app_id) {
        this.app_id = app_id;
        this.message = message;
    }
}
