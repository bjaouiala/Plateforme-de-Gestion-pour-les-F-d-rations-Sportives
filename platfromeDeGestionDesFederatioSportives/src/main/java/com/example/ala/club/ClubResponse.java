package com.example.ala.club;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String SportName;
    private byte[] clubPicture;
    private String description;
    private Double rate;
}
