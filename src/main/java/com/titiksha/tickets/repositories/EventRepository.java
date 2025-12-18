package com.titiksha.tickets.repositories;

import com.titiksha.tickets.domain.entities.Events;
import com.titiksha.tickets.domain.entities.EventsStatusEnum;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Events, UUID> {

  Page<Events> findByOrganizerId(UUID organizerId, Pageable pageable);

  Optional<Events> findByIdAndOrganizerId(UUID id, UUID organizerId);

  Page<Events> findByStatus(EventsStatusEnum status, Pageable pageable);

  @Query(value = "SELECT * FROM events WHERE " +
      "status = 'PUBLISHED' AND " +
      "to_tsvector('english', COALESCE(name, '') || ' ' || COALESCE(venue, '')) " +
      "@@ plainto_tsquery('english', :searchTerm)",
      countQuery = "SELECT count(*) FROM events WHERE " +
          "status = 'PUBLISHED' AND " +
          "to_tsvector('english', COALESCE(name, '') || ' ' || COALESCE(venue, '')) " +
          "@@ plainto_tsquery('english', :searchTerm)",
      nativeQuery = true)
  Page<Events> searchEvents(@Param("searchTerm") String searchTerm, Pageable pageable);

  Optional<Events> findByIdAndStatus(UUID id, EventsStatusEnum status);
}

