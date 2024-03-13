package com.alatoo.socialEventManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long eventId;
    private String eventName;
    private String description;
    private String location;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "event")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Comment> comments = new HashSet<>();
}
