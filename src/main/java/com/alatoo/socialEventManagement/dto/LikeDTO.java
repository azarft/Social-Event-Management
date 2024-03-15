package com.alatoo.socialEventManagement.dto;

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

    private UserDTO user;

    private EventDTO event;
}
