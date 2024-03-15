package com.alatoo.socialEventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDTO {


    private Long commentId;
    private String text;

    private UserDTO user;
    private EventDTO event;
}
