package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockMvc;

import com.alatoo.socialEventManagement.controllers.CommentApiController;
import com.alatoo.socialEventManagement.dto.CommentDTO;
import com.alatoo.socialEventManagement.services.comment.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentApiController.class)
public class CommentApiControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void testGetAllComments() throws Exception {
        // Given
        CommentDTO comment1 = CommentDTO.builder().commentId(1L).text("Comment 1").build();
        CommentDTO comment2 = CommentDTO.builder().commentId(2L).text("Comment 2").build();
        List<CommentDTO> commentList = Arrays.asList(comment1, comment2);

        Mockito.when(commentService.findAllComments()).thenReturn(commentList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(commentList.size()));
    }

    @Test
    public void testGetCommentById() throws Exception {
        // Given
        CommentDTO comment = CommentDTO.builder().commentId(1L).text("Comment 1").build();

        Mockito.when(commentService.findCommentById(1L)).thenReturn(Optional.of(comment));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentId").value(comment.getCommentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(comment.getText()));
    }

    @Test
    public void testCreateComment() throws Exception {
        // Given
        CommentDTO commentDTO = CommentDTO.builder().text("New Comment").build();

        Mockito.when(commentService.saveComment(commentDTO)).thenReturn(commentDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(commentDTO.getText()));
    }

    @Test
    public void testUpdateComment() throws Exception {
        // Given
        Long commentId = 1L;
        CommentDTO existingComment = CommentDTO.builder().commentId(commentId).text("Existing Comment").build();
        CommentDTO updatedComment = CommentDTO.builder().commentId(commentId).text("Updated Comment").build();

        Mockito.when(commentService.findCommentById(commentId)).thenReturn(Optional.of(existingComment));
        Mockito.when(commentService.saveComment(updatedComment)).thenReturn(updatedComment);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedComment)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(updatedComment.getText()));
    }

    @Test
    public void testDeleteComment() throws Exception {
        // Given
        Long commentId = 1L;

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to convert object to JSON string
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
