package com.example.ala.sport;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportRequest {
    @NotEmpty(message = "name must be present")
    @NotBlank(message = "name must be present")
    @Column(unique = true)
    private String name;
}
