package com.example.ala.adminClub;

import com.example.ala.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminRequest {
    private String firstname;
    private String lastname ;
    private String email;
    private String password;
    private String address;
    private long sportId;
    private Role role;
    private Boolean accountLocked;
    private byte[] userPicture;
}
