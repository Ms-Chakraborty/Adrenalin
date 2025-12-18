package com.titiksha.tickets.domain.dtos;

import com.titiksha.tickets.domain.entities.EventsStatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDetailsResponseDto {

  private UUID id;
  private String name;
  private LocalDateTime start;
  private LocalDateTime end;
  private String venue;
  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;
  private EventsStatusEnum status;
  private List<GetEventDetailsTicketTypesResponseDto> ticketTypes = new ArrayList<>();
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}


