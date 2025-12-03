package com.titiksha.tickets.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; 
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Assuming EventsStatusEnum is defined elsewhere
// Assuming User class is defined elsewhere (and is in the same package)

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "venue", nullable = true)
    private String venue;

    @Column(name = "sales_start")
    private LocalDateTime salesStart; // Corrected typo

    @Column(name = "sales_end")
    private LocalDateTime salesEnd; // Corrected typo

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventsStatusEnum status; // Assuming this Enum is defined

    // Relationship to User (Organizer)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    // Relationship to User (Attendees)
    @ManyToMany(mappedBy = "attendingEvents") // Corrected 'mappedby' to 'mappedBy'
    private List<User> attendees = new ArrayList<>();

    // Relationship to User (Staff)
    @ManyToMany(mappedBy = "staffingEvents") // Corrected 'mappedby' to 'mappedBy'
    private List<User> staff = new ArrayList<>();

    // Auditing Fields
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
