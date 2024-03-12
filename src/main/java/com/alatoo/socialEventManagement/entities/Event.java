package com.alatoo.socialEventManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private User createdBy;
}
