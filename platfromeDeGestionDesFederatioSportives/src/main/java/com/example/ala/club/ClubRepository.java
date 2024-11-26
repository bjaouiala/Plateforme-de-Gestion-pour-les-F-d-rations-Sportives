package com.example.ala.club;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {
    @Query("""
SELECT club
FROM Club club
WHERE club.sport.name = :name
""")

    Page<Club> findBySportName(String name, Pageable pageable);

    List<Club> findBySportId(Long id);
}
