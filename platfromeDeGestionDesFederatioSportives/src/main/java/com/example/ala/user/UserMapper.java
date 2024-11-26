package com.example.ala.user;

import com.example.ala.File.FileStorageService;
import com.example.ala.File.FileUtils;
import com.example.ala.adminClub.AdminResponse;
import com.example.ala.authentication.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    public User toUser(RegistrationRequest request, MultipartFile picture){
        return User.builder().userPicture(request.getUserPicture())
                .address(request.getAddress())
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .enabled(true)
                .userPicture(fileStorageService.saveFile(picture, request.getFirstname()))
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .email(request.getEmail())
                .build();
    }

    public AdminResponse fromUser(User user) {
        return AdminResponse.builder()
                .id(user.getId())
                .sportId(user.getSport().getId())
                .address(user.getAddress())
                .firstname(user.getFirstname())
                .email(user.getEmail())
                .lastname(user.getLastname())
                .userPicture(FileUtils.readFileFromLocation(user.getUserPicture()))
                .role(user.getRole())
                .accountLocked(user.isAccountLocked())
                .build();

    }
}
