package com.alatoo.socialEventManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "\"user\"")
public class Like {
    @Id
    @GeneratedValue
    private Long likeId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
}
