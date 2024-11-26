package com.example.ala.club;

import com.example.ala.club.feedback.Feedback;
import com.example.ala.sport.Sport;
import com.example.ala.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Club {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String clubPicture;
    private String description;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private Date createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private Date lastModified;
    @ManyToOne
    private Sport sport;
    @OneToMany(mappedBy = "club")
    private List<User> users;
    @OneToMany(mappedBy = "club")
    List<Feedback> feedbacks;
    @Transient
    public double getRate(){
        if (feedbacks == null || feedbacks.isEmpty()){
            return  0.0;
        }
        var rate = this.feedbacks.stream().mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0) / 10.0;
    }
}
