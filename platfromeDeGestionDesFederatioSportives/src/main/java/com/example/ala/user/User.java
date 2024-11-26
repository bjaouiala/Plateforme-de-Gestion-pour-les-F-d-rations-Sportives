package com.example.ala.user;

import com.example.ala.club.Club;
import com.example.ala.event.Event;
import com.example.ala.sport.Sport;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@ToString
@Table(name = "base_user")
public class User implements UserDetails,Principal{
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname ;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String document;
    private String userPicture;
    private boolean enabled;
    private boolean accountLocked;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private Date lastModifiedDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_relationship",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @ManyToMany(mappedBy = "users")
    private List<User> userList;
    @ManyToOne(fetch = FetchType.EAGER)
    private Sport sport;
    @OneToMany
    List<Token> tokens;
    @ManyToOne
    private Club club;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return email;
    }
    public String fullName(){
        return firstname+" "+lastname;
    }
}
