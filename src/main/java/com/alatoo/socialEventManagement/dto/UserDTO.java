package com.alatoo.socialEventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String name;

    private Set<EventDTO> createdEvents;

    private Set<LikeDTO> likes;

    private Set<CommentDTO> comments;
}
