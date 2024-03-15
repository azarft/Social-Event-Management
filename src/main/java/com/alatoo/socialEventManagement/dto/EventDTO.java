package com.alatoo.socialEventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDTO {

    private Long eventId;
    private String eventName;
    private String description;
    private String location;
    private Date date;

    private UserDTO createdBy;

    private Set<LikeDTO> likes;

    private Set<CommentDTO> comments;
}
