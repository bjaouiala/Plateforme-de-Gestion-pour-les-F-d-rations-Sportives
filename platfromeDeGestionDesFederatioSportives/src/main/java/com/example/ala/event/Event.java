package com.example.ala.event;

import com.example.ala.sport.Sport;
import com.example.ala.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private Date createdDate;
    @LastModifiedDate
    @Column(updatable = false)
    private Date lastModifiedDate;
    @CreatedBy
    @Column(nullable = false,updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;
    @ManyToMany
    private List<User> users;
    @ManyToOne
    private Sport sport;
    private String eventPicture;



}
