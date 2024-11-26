package com.example.ala.adminClub;

import com.example.ala.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminResponse {
    private Long id;
    private String firstname;
    private String lastname ;
    private String email;
    private String password;
    private String address;
    private long sportId;
    private Role role;
    private byte[] userPicture;
    private Boolean accountLocked;
}
