package com.example.ala.club.feedback;

import com.example.ala.club.Club;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;
    private Double note;
    @ManyToOne
    private Club club;
}
