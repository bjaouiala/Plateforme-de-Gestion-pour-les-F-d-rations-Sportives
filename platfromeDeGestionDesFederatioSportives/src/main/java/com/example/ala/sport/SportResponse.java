package com.example.ala.sport;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SportResponse {
    private Long id;
    private String name;
}
