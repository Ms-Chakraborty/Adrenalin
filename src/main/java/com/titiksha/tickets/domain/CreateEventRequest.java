package com.titiksha.tickets.domain;

import com.titiksha.tickets.domain.entities.EventsStatusEnum;
import com.titiksha.tickets.domain.entities.TicketType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.titiksha.tickets.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {

  private String name;
  private LocalDateTime start;
  private LocalDateTime end;
  private String venue;
  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;
  private EventsStatusEnum status;
  private User organizer;
  private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
