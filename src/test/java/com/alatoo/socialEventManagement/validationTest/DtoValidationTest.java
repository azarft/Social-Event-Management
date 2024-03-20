package com.alatoo.socialEventManagement.validationTest;

import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.exceptionTest.GlobalExceptionHandlerTest;
import com.alatoo.socialEventManagement.controllers.exceptions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class DtoValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidUserDto() throws Exception {
        String invalidUserJson = "{\"email\":\"invalidemail.com\",\"name\":\"John Doe\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testInvalidEventDto() throws Exception {
        String invalidEventJson = "{\"eventName\":\"Event Name\",\"location\":\"Location\",\"date\":\"2024-12-31\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEventJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void testInvalidCommentDto() throws Exception {
        String invalidCommentJson = "{\"text\":\"This is a comment\",\"user\":{}}"; // Incomplete JSON for demonstration

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCommentJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}

