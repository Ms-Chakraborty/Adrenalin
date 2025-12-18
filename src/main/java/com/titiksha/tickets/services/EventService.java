package com.titiksha.tickets.services;

import com.titiksha.tickets.domain.CreateEventRequest;
import com.titiksha.tickets.domain.UpdateEventRequest;
import com.titiksha.tickets.domain.entities.Events;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

  Events createEvent(UUID organizerId, CreateEventRequest event);

  Page<Events> listEventsForOrganizer(UUID organizerId, Pageable pageable);

  Optional<Events> getEventForOrganizer(UUID organizerId, UUID id);

  Events updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event);

  void deleteEventForOrganizer(UUID organizerId, UUID id);

  Page<Events> listPublishedEvents(Pageable pageable);

  Page<Events> searchPublishedEvents(String query, Pageable pageable);

  Optional<Events> getPublishedEvent(UUID id);
}
