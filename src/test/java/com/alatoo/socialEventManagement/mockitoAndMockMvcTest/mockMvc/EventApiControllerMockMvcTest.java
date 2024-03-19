package com.alatoo.socialEventManagement.mockitoAndMockMvcTest.mockMvc;

import com.alatoo.socialEventManagement.controllers.EventApiController;
import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.services.event.EventService;
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
@WebMvcTest(EventApiController.class)
public class EventApiControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllEvents() throws Exception {
        // Given
        EventDTO event1 = EventDTO.builder().eventId(1L).eventName("Event 1").build();
        EventDTO event2 = EventDTO.builder().eventId(2L).eventName("Event 2").build();
        List<EventDTO> eventList = Arrays.asList(event1, event2);

        Mockito.when(eventService.findAllEvents()).thenReturn(eventList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(eventList.size()));
    }

    @Test
    public void testGetEventById() throws Exception {
        // Given
        Long eventId = 1L;
        EventDTO event = EventDTO.builder().eventId(eventId).eventName("Event 1").build();

        Mockito.when(eventService.findEventByID(eventId)).thenReturn(Optional.of(event));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/event/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(event.getEventId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName").value(event.getEventName()));
    }

    @Test
    public void testCreateEvent() throws Exception {
        // Given
        EventDTO eventDTO = EventDTO.builder().eventName("New Event").build();
        EventDTO createdEvent = EventDTO.builder().eventId(1L).eventName("New Event").build();

        Mockito.when(eventService.saveEvent(Mockito.any(EventDTO.class))).thenReturn(createdEvent);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(createdEvent.getEventId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName").value(createdEvent.getEventName()));
    }

    @Test
    public void testUpdateEvent() throws Exception {
        // Given
        Long eventId = 1L;
        EventDTO eventDTO = EventDTO.builder().eventId(eventId).eventName("Updated Event").build();

        Mockito.when(eventService.findEventByID(eventId)).thenReturn(Optional.of(eventDTO));
        Mockito.when(eventService.saveEvent(Mockito.any(EventDTO.class))).thenReturn(eventDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/event/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(eventDTO.getEventId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName").value(eventDTO.getEventName()));
    }

    @Test
    public void testDeleteEvent() throws Exception {
        // Given
        Long eventId = 1L;

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/event/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
