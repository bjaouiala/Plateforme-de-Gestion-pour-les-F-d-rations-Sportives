package com.example.ala.authentication;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    private String email;
    private String password;
}
