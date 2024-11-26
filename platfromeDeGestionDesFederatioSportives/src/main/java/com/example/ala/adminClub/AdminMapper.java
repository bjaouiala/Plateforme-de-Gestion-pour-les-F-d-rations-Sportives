package com.example.ala.adminClub;

import com.example.ala.File.FileUtils;
import com.example.ala.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@Getter
@RequiredArgsConstructor
public class AdminMapper {
private final PasswordEncoder encoder;
    public AdminResponse fromUser(User user) {
        return AdminResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .userPicture(FileUtils.readFileFromLocation(user.getUserPicture()))
                .sportId(user.getSport().getId())
                .address(user.getAddress()).build();

    }
}
