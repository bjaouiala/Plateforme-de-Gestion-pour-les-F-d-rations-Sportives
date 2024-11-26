package com.example.ala.event;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
public class EventRequest {
    private Long id;
    @NotBlank(message = "please enter name")
    @NotEmpty(message = "please enter name")
    private String name;
    @NotBlank(message = "please enter description")
    @NotEmpty(message = "please enter description")
    private String description;
    @NotNull(message = "please enter startDate")
    private LocalDate startDate;
    @NotNull(message = "please enter endDate")
    private LocalDate endDate;
    @NotBlank(message = "please enter location")
    @NotEmpty(message = "please enter location")
    private String location;
    private Status status;
}
