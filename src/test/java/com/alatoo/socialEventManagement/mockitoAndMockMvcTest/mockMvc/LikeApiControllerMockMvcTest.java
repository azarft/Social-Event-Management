package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockMvc;

import com.alatoo.socialEventManagement.controllers.LikeApiController;
import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.services.like.LikeService;
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
@WebMvcTest(LikeApiController.class)
public class LikeApiControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeService likeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllLikes() throws Exception {
        // Given
        LikeDTO like1 = LikeDTO.builder().likeId(1L).build();
        LikeDTO like2 = LikeDTO.builder().likeId(2L).build();
        List<LikeDTO> likeList = Arrays.asList(like1, like2);

        Mockito.when(likeService.findAllLikes()).thenReturn(likeList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/like")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(likeList.size()));
    }

    @Test
    public void testGetLikeById() throws Exception {
        // Given
        Long likeId = 1L;
        LikeDTO like = LikeDTO.builder().likeId(likeId).build();

        Mockito.when(likeService.findLikeById(likeId)).thenReturn(Optional.of(like));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/like/{id}", likeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.likeId").value(like.getLikeId()));
    }

    @Test
    public void testCreateLike() throws Exception {
        // Given
        LikeDTO likeDTO = LikeDTO.builder().likeId(1L).build();

        Mockito.when(likeService.saveLike(Mockito.any(LikeDTO.class))).thenReturn(likeDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.likeId").value(likeDTO.getLikeId()));
    }

    @Test
    public void testUpdateLike() throws Exception {
        // Given
        Long likeId = 1L;
        LikeDTO likeDTO = LikeDTO.builder().likeId(likeId).build();

        Mockito.when(likeService.findLikeById(likeId)).thenReturn(Optional.of(likeDTO));
        Mockito.when(likeService.saveLike(Mockito.any(LikeDTO.class))).thenReturn(likeDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/like/{id}", likeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.likeId").value(likeDTO.getLikeId()));
    }

    @Test
    public void testDeleteLike() throws Exception {
        // Given
        Long likeId = 1L;

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/like/{id}", likeId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
