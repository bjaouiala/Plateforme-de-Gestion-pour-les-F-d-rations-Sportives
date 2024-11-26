package com.example.ala.user;

import com.example.ala.sport.Sport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findBySportAndRole(Sport sport, Role role);
    @Query("""
            SELECT admin
            FROM User admin
            WHERE admin.sport.id = :id
            AND admin.role = 'ADMIN_CLUB'
            """)
    Page<User> findByRoleAndSportName(Pageable pageable, Long id);
}
