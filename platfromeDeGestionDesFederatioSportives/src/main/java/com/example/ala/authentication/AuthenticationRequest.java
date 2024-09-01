package com.example.ala.authentication;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @Email(message = "email is not formatted")
    @NotBlank(message = "enter your email please")
    @NotEmpty(message = "enter your email please")
    private String email;
    @Size(min = 8,message = "password should be grater then 8 character")
    @NotBlank(message = "enter your password please")
    @NotEmpty(message = "enter your password please")
    private String password;
}
