package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockito;

import com.alatoo.socialEventManagement.controllers.LikeApiController;
import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.services.like.LikeService;
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

public class LikeApiControllerTest {

    @Mock
    private LikeService likeService;

    @InjectMocks
    private LikeApiController likeApiController;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for testing the getAllLikes method.
     */
    @Test
    void testGetAllLikes() {
        // Prepare mock data
        List<LikeDTO> likes = new ArrayList<>();
        likes.add(LikeDTO.builder().likeId(2L).build());
        likes.add(LikeDTO.builder().likeId(1L).build());

        // Define behavior for likeService mock
        when(likeService.findAllLikes()).thenReturn(likes);

        // Perform get all likes action
        List<LikeDTO> result = likeApiController.getAllLikes();

        // Verify that the findAllLikes method of likeService is called once
        verify(likeService, times(1)).findAllLikes();

        // Assert that the result returned by the controller matches the expected result
        assertEquals(likes, result);
    }

    /**
     * Test case for testing the getById method.
     */
    @Test
    void testGetById() {
        // Prepare mock data
        LikeDTO like = LikeDTO.builder().likeId(1L).build();

        // Define behavior for likeService mock
        when(likeService.findLikeById(1L)).thenReturn(Optional.of(like));

        // Perform get like by ID action
        LikeDTO result = likeApiController.getById(1L);

        // Verify that the findLikeById method of likeService is called once with the correct ID
        verify(likeService, times(1)).findLikeById(1L);

        // Assert that the result returned by the controller matches the expected result
        assertEquals(like, result);
    }

    /**
     * Test case for testing the deleteById method.
     */
    @Test
    void testDeleteLike() {
        // Call the controller method
        likeApiController.deleteLike(1L);

        // Verify that the deleteLike method of likeService is called once with the correct ID
        verify(likeService, times(1)).deleteLike(1L);
    }

    /**
     * Test case for testing the createLike method.
     */
    @Test
    void testCreateLike() {
        // Prepare mock data
        LikeDTO likeDTO = LikeDTO.builder().likeId(1L).build();
        when(likeService.saveLike(likeDTO)).thenReturn(likeDTO);

        // Call the controller method
        LikeDTO result = likeApiController.createLike(likeDTO);

        // Verify that the saveLike method of likeService is called once with the correct LikeDTO object
        verify(likeService, times(1)).saveLike(likeDTO);

        // Assert that the result returned by the controller matches the expected result
        assertEquals(likeDTO, result);
    }
}
