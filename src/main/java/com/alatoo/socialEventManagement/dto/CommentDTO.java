package com.alatoo.socialEventManagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDTO {

    private Long commentId;

    @NotBlank(message = "Comment text is required")
    private String text;

    @NotNull(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Event is required")
    private EventDTO event;
}
