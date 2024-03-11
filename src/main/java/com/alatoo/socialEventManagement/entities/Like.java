package com.alatoo.socialEventManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Like {
    @Id
    @GeneratedValue
    private Long likeId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
}
