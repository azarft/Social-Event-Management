package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockMvc;

import com.alatoo.socialEventManagement.controllers.UserApiController;
import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
@WebMvcTest(UserApiController.class)
public class UserApiControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        // Given
        UserDTO user1 = UserDTO.builder().id(1L).username("John").email("john@example.com").name("John Doe").build();
        UserDTO user2 = UserDTO.builder().id(2L).username("Jane").email("jane@example.com").name("Jane Doe").build();
        List<UserDTO> userList = Arrays.asList(user1, user2);

        Mockito.when(userService.findAllUsers()).thenReturn(userList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(userList.size()));
    }

    @Test
    public void testGetUserById() throws Exception {
        // Given
        Long userId = 1L;
        UserDTO user = UserDTO.builder().id(userId).username("John").email("john@example.com").name("John Doe").build();

        Mockito.when(userService.findUserByID(userId)).thenReturn(Optional.of(user));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));
    }

    @Test
    public void testCreateUser() throws Exception {
        // Given
        UserDTO userDTO = UserDTO.builder().id(1L).username("John").email("john@example.com").name("John Doe").build();
        String requestBody = new ObjectMapper().writeValueAsString(userDTO);

        Mockito.when(userService.saveUser(Mockito.any(UserDTO.class))).thenReturn(userDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(userDTO.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(userDTO.getName()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Given
        Long userId = 1L;
        UserDTO userDTO = UserDTO.builder().id(userId).username("John").email("john@example.com").name("John Doe").build();
        String requestBody = new ObjectMapper().writeValueAsString(userDTO);

        Mockito.when(userService.findUserByID(userId)).thenReturn(Optional.of(userDTO));
        Mockito.when(userService.saveUser(Mockito.any(UserDTO.class))).thenReturn(userDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(userDTO.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(userDTO.getName()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Given
        Long userId = 1L;

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}