package com.example.ala.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    ENTITY_NOTFOUND(404, HttpStatus.NOT_FOUND,"the entity does not exist"),
    ACCOUNT_LOCKED(200, HttpStatus.FORBIDDEN,"this account is locked"),
    ACCOUNT_DISABLED(201,HttpStatus.FORBIDDEN,"this account is disabled"),
    BAD_CREDENTIALS(201,HttpStatus.FORBIDDEN,"email or password incorrect"),
    INCORRECT_CURRENT_PASSWORD(300,HttpStatus.BAD_REQUEST,"current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301,HttpStatus.BAD_REQUEST,"the new password does not match");

    private final Integer code;
    private final HttpStatus status;
    private final String description;

    BusinessError(Integer code, HttpStatus status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }


}
