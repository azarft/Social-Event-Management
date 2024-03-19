package com.alatoo.socialEventManagement.mockitoAndMockMvcTest;

import com.alatoo.socialEventManagement.controllers.UserApiController;
import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.services.user.UserService;
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

/**
 * Unit tests for the UserApiController class using Mockito.
 */
class UserApiControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserApiController userApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for testing the getAllUsers method.
     */
    @Test
    void testGetAllUsers() {
        // Prepare mock data
        List<UserDTO> users = new ArrayList<>();
        users.add(UserDTO.builder().id(1L).username("user1").build());
        users.add(UserDTO.builder().id(2L).username("user2").build());
        when(userService.findAllUsers()).thenReturn(users);

        // Call the controller method
        List<UserDTO> result = userApiController.getAllUsers();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(users, result);
    }

    /**
     * Test case for testing the getById method.
     */
    @Test
    void testGetById() {
        // Prepare mock data
        UserDTO user = UserDTO.builder().id(1L).username("user1").build();
        when(userService.findUserByID(1L)).thenReturn(Optional.of(user));

        // Call the controller method
        UserDTO result = userApiController.getById(1L);

        // Verify the result
        assertEquals(user, result);
    }

    /**
     * Test case for testing the createUser method.
     */
    @Test
    void testCreateUser() {
        // Prepare mock data
        UserDTO userDTO = UserDTO.builder().id(1L).username("user1").build();
        when(userService.saveUser(userDTO)).thenReturn(userDTO);

        // Call the controller method
        UserDTO result = userApiController.createUser(userDTO);

        // Verify the result
        assertEquals(userDTO, result);
    }

    /**
     * Test case for testing the updateUser method.
     */
    @Test
    void testUpdateUser() {
        // Prepare mock data
        UserDTO userDTO = UserDTO.builder().id(1L).username("user1").build();
        when(userService.findUserByID(1L)).thenReturn(Optional.of(userDTO));
        when(userService.saveUser(userDTO)).thenReturn(userDTO);

        // Call the controller method
        UserDTO result = userApiController.updateUser(1L, userDTO);

        // Verify the result
        assertEquals(userDTO, result);
    }

    /**
     * Test case for testing the deleteUser method.
     */
    @Test
    void testDeleteUser() {
        // Call the controller method
        userApiController.deleteUser(1L);

        // Verify that the delete method of UserService is called once with the correct ID
        verify(userService, times(1)).deleteUser(1L);
    }
}
