package com.alatoo.socialEventManagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDTO {

    private Long eventId;

    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Date is required")
    private Date date;

    @NotNull(message = "Created by user is required")
    private UserDTO createdBy;

    private Set<LikeDTO> likes;

    private Set<CommentDTO> comments;
}
