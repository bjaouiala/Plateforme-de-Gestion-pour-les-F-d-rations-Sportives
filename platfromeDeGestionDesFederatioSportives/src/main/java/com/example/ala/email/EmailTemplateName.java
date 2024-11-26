package com.example.ala.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    SIMPLE_USER_ACTIVATION_ACCOUNT("simple_user_activation_account.html")
    ,ATHLETE_ACTIVATION_ACCOUNT("athlete_activation_account.html"),
    ADMIN_ACTIVATION_ACCOUNT("admin_activation_account.html");
    private final String name;

    EmailTemplateName(String name) {
        this.name=name;
    }
}
