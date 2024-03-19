package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockito;

import com.alatoo.socialEventManagement.controllers.CommentApiController;
import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.services.comment.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommentApiControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentApiController commentApiController;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for testing the getAllComments method.
     */
    @Test
    void getAllComments() {
        // Prepare mock data
        List<CommentDTO> comments = new ArrayList<>();
        comments.add(CommentDTO.builder().commentId(1L).text("Comment 1").build());
        comments.add(CommentDTO.builder().commentId(2L).text("Comment 2").build());
        when(commentService.findAllComments()).thenReturn(comments);

        // Perform get all comments action
        List<CommentDTO> result = commentApiController.getAllComments();

        // Verify that the findAllComments method of commentService is called once
        verify(commentService, times(1)).findAllComments();

        // Assert that the result returned by the controller matches the expected result
        assertEquals(2, result.size());
        assertEquals(comments, result);
    }

    /**
     * Test case for testing the getById method.
     */
    @Test
    void testGetById() {
        // Prepare mock data
        CommentDTO comment = CommentDTO.builder().commentId(1L).text("Comment 1").build();
        when(commentService.findCommentById(1L)).thenReturn(Optional.of(comment));

        // Perform get comment by ID action
        CommentDTO result = commentApiController.getById(1L);

        // Verify that the findCommentById method of commentService is called once with the correct ID
        verify(commentService, times(1)).findCommentById(1L);

        // Assert that the result returned by the controller matches the expected result
        assertEquals(comment, result);
    }

    /**
     * Test case for testing the createComment method.
     */
    @Test
    void testCreateComment() {
        // Prepare mock data
        CommentDTO commentDTO = CommentDTO.builder().commentId(1L).text("Comment 1").build();
        when(commentService.saveComment(commentDTO)).thenReturn(commentDTO);

        // Call the controller method
        CommentDTO result = commentApiController.createComment(commentDTO);

        // Verify that the saveComment method of commentService is called once with the correct CommentDTO object
        verify(commentService, times(1)).saveComment(commentDTO);

        // Assert that the result returned by the controller matches the expected result
        assertEquals(commentDTO, result);
    }

    /**
     * Test case for testing the updateComment method.
     */
    @Test
    void testUpdateComment() {
        // Prepare mock data
        CommentDTO commentDTO = CommentDTO.builder().commentId(1L).text("Comment 1").build();
        when(commentService.findCommentById(1L)).thenReturn(Optional.of(commentDTO));
        when(commentService.saveComment(commentDTO)).thenReturn(commentDTO);

        // Call the controller method
        CommentDTO result = commentApiController.updateComment(1L, commentDTO);

        // Verify that the findCommentById method of commentService is called once with the correct ID
        verify(commentService, times(1)).findCommentById(1L);
        // Verify that the saveComment method of commentService is called once with the correct CommentDTO object
        verify(commentService, times(1)).saveComment(commentDTO);

        // Assert that the result returned by the controller matches the expected result
        assertEquals(commentDTO, result);
    }

    /**
     * Test case for testing the deleteComment method.
     */
    @Test
    void testDeleteComment() {
        // Call the controller method
        commentApiController.deleteComment(1L);

        // Verify that the delete method of CommentService is called once with the correct ID
        verify(commentService, times(1)).deleteComment(1L);
    }
}
