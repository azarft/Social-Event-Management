package com.alatoo.socialEventManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;
}
