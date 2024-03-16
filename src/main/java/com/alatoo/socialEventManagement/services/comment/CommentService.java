package com.alatoo.socialEventManagement.services.comment;

import com.alatoo.socialEventManagement.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDTO> findAllComments();

    Optional<CommentDTO> findCommentById(Long id);

    CommentDTO saveComment(CommentDTO dto);

    void deleteComment(Long id);
}