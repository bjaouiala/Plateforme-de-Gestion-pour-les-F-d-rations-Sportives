package com.example.ala.authentication;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService userService;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse auth = userService.authenticate(authenticationRequest);
        String jwtToken = auth.getToken();
        ResponseCookie jwtCookie = ResponseCookie.from("auth_token",jwtToken)
                .httpOnly(true)
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
                .body(auth);
    }



    @GetMapping("/activate-account")
    public void confirm(@RequestParam String token) throws MessagingException {
        userService.activateAccount(token);
    }
    @PostMapping("/resent-CodeConfirmation")
    public ResponseEntity<?> resentConfirmationToken(@RequestBody String email) throws MessagingException {
        userService.resentConfirmationToken(email);
        return ResponseEntity.accepted().build();
    }


}
