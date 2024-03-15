package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.entities.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
    Comment commentDtoToComment(CommentDTO dto);

    CommentDTO commentToCommentDto(Comment comment);
}
