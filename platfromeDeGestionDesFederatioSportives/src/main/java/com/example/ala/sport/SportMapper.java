package com.example.ala.sport;

import org.springframework.stereotype.Service;

@Service
public class SportMapper {
    public Sport toSport(SportRequest request){
        return Sport.builder().name(request.getName()).build();
    }
    public SportResponse fromSport(Sport sport){
        return SportResponse.builder()
                .id(sport.getId())
                .name(sport.getName())
                .build();
    }
}
