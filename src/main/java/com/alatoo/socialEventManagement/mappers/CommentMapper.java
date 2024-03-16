package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {

    Comment commentDtoToComment(CommentDTO dto);

    @Mapping(target = "event", ignore = true) // Ignore event to prevent circular reference
    @Mapping(target = "user", ignore = true)
    CommentDTO commentToCommentDto(Comment comment);
}
