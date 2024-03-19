package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.controllers.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.services.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1")
public class CommentApiController {
    private final String COMMENT_PATH = "/comment";
    private final String ID_PATH = COMMENT_PATH + "/{id}";

    private final CommentService commentService;

    @GetMapping(COMMENT_PATH)
    public List<CommentDTO> getAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping(ID_PATH)
    public CommentDTO getById(@PathVariable Long id) {
        log.info("Getting comment with id: {}", id);
        return commentService.findCommentById(id).orElseThrow(() -> new NotFoundException("Comment not found with id: " + id));
    }

    @PostMapping(COMMENT_PATH)
    public CommentDTO createComment(@Validated @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(commentDTO);
    }

    @PutMapping(ID_PATH)
    public CommentDTO updateComment(@PathVariable Long id, @Validated @RequestBody CommentDTO commentDTO) {
        commentService.findCommentById(id).orElseThrow(() -> new NotFoundException("Comment not found with id: " + id));
        commentDTO.setCommentId(id);
        return commentService.saveComment(commentDTO);
    }

    @DeleteMapping(ID_PATH)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
