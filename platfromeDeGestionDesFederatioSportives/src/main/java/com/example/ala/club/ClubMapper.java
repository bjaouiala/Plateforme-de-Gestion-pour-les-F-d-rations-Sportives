package com.example.ala.club;

import com.example.ala.File.FileUtils;
import com.example.ala.sport.Sport;
import org.springframework.stereotype.Service;

@Service
public class ClubMapper {
    public ClubResponse fromClub(Club club){
        return ClubResponse
                .builder()
                .email(club.getEmail())
                .SportName(club.getSport().getName())
                .address(club.getAddress())
                .phoneNumber(club.getPhoneNumber())
                .name(club.getName())
                .clubPicture(FileUtils.readFileFromLocation(club.getClubPicture()))
                .description(club.getDescription())
                .rate(club.getRate())
                .id(club.getId())
                .build();
    }

    public Club toClub(ClubRequest club, Sport sport){
        return Club
                .builder()
                .email(club.getEmail())
                .sport(sport)
                .address(club.getAddress())
                .phoneNumber(club.getPhoneNumber())
                .name(club.getName())
                .clubPicture(club.getClubPicture())
                .description(club.getDescription())
                .build();
    }

}
