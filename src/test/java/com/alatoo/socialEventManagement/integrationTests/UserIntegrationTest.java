package com.alatoo.socialEventManagement.integrationTests;

import com.alatoo.socialEventManagement.controllers.UserApiController;
import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import com.alatoo.socialEventManagement.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() throws Exception {
        UserDTO user1 = UserDTO.builder().id(1L).username("azanov_argen").build();
        UserDTO user2 = UserDTO.builder().id(2L).username("malikov_adilet").build();
        List<UserDTO> userList = Arrays.asList(user1, user2);

        when(userService.findAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(userList.size()));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        UserDTO user = UserDTO.builder().id(userId).username("azanov_argen").build();

        when(userService.findUserByID(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
    }

    // Add more integration tests for other endpoints and scenarios
}
