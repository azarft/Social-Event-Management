package com.alatoo.socialEventManagement.services.comment;

import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.entities.Comment;
import com.alatoo.socialEventManagement.mappers.CommentMapper;
import com.alatoo.socialEventManagement.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceJPA implements CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceJPA(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDTO> findAllComments() {
        List<Comment> events = (List<Comment>) commentRepository.findAll();
        return events.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentDTO> findCommentById(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        return Optional.ofNullable(
                commentMapper.commentToCommentDto(optionalComment.orElse(null))
        );
    }

    @Override
    public CommentDTO saveComment(CommentDTO dto) {
        Comment savedComment = commentRepository.save(commentMapper.commentDtoToComment(dto));
        return commentMapper.commentToCommentDto(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
