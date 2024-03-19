package com.alatoo.socialEventManagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LikeDTO {

    private Long likeId;

    @NotNull(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Event is required")
    private EventDTO event;
}
