package com.example.ala.config;

import com.example.ala.user.Role;
import com.example.ala.user.User;
import com.example.ala.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class BeansConfig{

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;


    @Bean
   public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.AUTHORIZATION
        ));
        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "DELETE",
                "PUT",
                "PATCH"
        ));
        source.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(source);
    }

    @Bean
    public CommandLineRunner createDefault(){
        return args -> {
            if (!userRepository.existsByEmail("football@gmail.com")){
               var user = User.builder()
                        .email("football@gmail.com")
                        .password(passwordEncoder().encode("12345678"))
                        .firstname("ala")
                        .lastname("bjaoui")
                        .address("ariana")
                        .enabled(true)
                        .accountLocked(false)
                        .role(Role.ADMIN_FEDERATION)
                        .build();
               userRepository.save(user);

            }else if (!userRepository.existsByEmail("handball@gmail.com")){
                var user = User.builder()
                        .email("handball@gmail.com")
                        .password(passwordEncoder().encode("12345678"))
                        .firstname("ala")
                        .lastname("handball")
                        .address("tunis")
                        .enabled(true)
                        .accountLocked(false)
                        .role(Role.ADMIN_FEDERATION)
                        .build();
                userRepository.save(user);
            }
        };
    }

    @Bean
    public AuditorAware<String> auditorAware(){
        return new ApplicationAuditAware();
    }


}
