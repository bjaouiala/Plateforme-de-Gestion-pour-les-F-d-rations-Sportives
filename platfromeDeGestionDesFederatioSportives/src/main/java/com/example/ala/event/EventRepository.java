package com.example.ala.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event,Long>{

    @Query("""
       SELECT event
       FROM Event event
       WHERE event.sport.name = :sportName
       AND event.status = :status
       """)
    Page<Event> findFinishedEventsBySportName(String sportName, Pageable pageable,Status status);

    @Query("""
       SELECT event
       FROM Event event
       WHERE event.sport.name = :sportName
       """)

    Page<Event> findAllEventsBySportName(String sportName, Pageable pageable);
}
