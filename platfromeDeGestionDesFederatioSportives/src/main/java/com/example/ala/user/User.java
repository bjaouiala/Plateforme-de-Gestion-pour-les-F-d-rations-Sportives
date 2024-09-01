package com.example.ala.user;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.LifecycleState;
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
@Table(name = "base_user")
public class User implements UserDetails,Principal{
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname ;
    private String email;
    private String password;
    private String address;
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
    @ManyToMany
    @JoinTable(
            name = "user_relationship",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    @ManyToMany(mappedBy = "users")
    private List<User> userList;


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
