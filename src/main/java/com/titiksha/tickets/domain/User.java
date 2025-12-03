package com.titiksha.tickets.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener; // Required for @CreatedDate/@LastModifiedDate

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners; // Required for Auditing
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

// Enable JPA Auditing listeners for @CreatedDate and @LastModifiedDate
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) 
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true) // Email should typically be unique
    private String email;

    // A user can organize many Events (One-to-Many)
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Events> organizingEvents = new ArrayList<>();

    // A user can attend many Events (Many-to-Many)
    @ManyToMany
    @JoinTable(
        name = "user_attending_events", // Corrected 'user' to 'name'
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Events> attendingEvents = new ArrayList<>();

    // A user can be staff for many Events (Many-to-Many)
    @ManyToMany
    @JoinTable(
        name = "user_staffing_events",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Events> staffingEvents = new ArrayList<>();

    // Auditing Fields
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}