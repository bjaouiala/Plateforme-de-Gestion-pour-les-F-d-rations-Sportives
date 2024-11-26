package com.example.ala.sport;

import com.example.ala.club.Club;
import com.example.ala.event.Event;
import com.example.ala.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sport {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "sport")
    private List<User> users;
    @OneToMany(mappedBy = "sport")
    private List<Club> clubs;
    @OneToMany(mappedBy = "sport")
    private List<Event> events;
}
