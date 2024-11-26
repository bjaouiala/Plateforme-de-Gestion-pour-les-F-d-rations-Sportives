package com.example.ala.club;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubRequest {
    private Long id;
    @NotBlank(message = "please enter the club name")
    @NotEmpty(message = "please enter the club name")
    private String name;
    @NotBlank(message = "please enter the club address")
    @NotEmpty(message = "please enter the club address")
    private String address;
    @NotBlank(message = "please enter the club phone")
    @NotEmpty(message = "please enter the club phone")
    private String phoneNumber;
    @NotBlank(message = "please enter the club email")
    @NotEmpty(message = "please enter the club email")
    @Email(message = "email not valid")
    private String email;
    @NotBlank(message = "please enter the club description")
    @NotEmpty(message = "please enter the club description")
    private String description;
    private String ClubPicture;
}
