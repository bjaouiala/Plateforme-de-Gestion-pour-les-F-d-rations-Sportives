package com.example.ala.authentication;


import com.example.ala.File.FileStorageService;
import com.example.ala.email.EmailService;
import com.example.ala.email.EmailTemplateName;
import com.example.ala.security.JwtService;
import com.example.ala.sport.Sport;
import com.example.ala.sport.SportRepository;
import com.example.ala.user.*;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final UserMapper mapper;
    private final FileStorageService fileStorageService;
    private final SportRepository sportRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${application.mail.activation-url}")
    private String confirmUrl;
    @Value("${application.mail.login-url}")
    private String loginUrl;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );
        var user = (User)auth.getPrincipal();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }




    private void sendUserValidationAccount(User user) throws MessagingException {
        String codeConfirmation = generateAndSaveActivationToken(user);
        emailService.senUserEmailVerification(user.getEmail(), "bjaouialaa@gmail.com", user.fullName(),
                EmailTemplateName.SIMPLE_USER_ACTIVATION_ACCOUNT,"Account Activation",confirmUrl,codeConfirmation

        );

    }

    private String generateAndSaveActivationToken(User user) {
        String token = generateActivationCode(6);
        var savedtoken = Token
                .builder()
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(savedtoken);
        return token ;
    }
    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length ; i++){
            int index= secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }
        return codeBuilder.toString();
    }


    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(()-> new RuntimeException("invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendUserValidationAccount(savedToken.getUser());
            throw new RuntimeException("token has been expired a new token sent");
        }
        User user = savedToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidateAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public void resentConfirmationToken(String email) throws MessagingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("user not found"));
        if (!user.isEnabled()){
            sendUserValidationAccount(user);
        }
    }
}
