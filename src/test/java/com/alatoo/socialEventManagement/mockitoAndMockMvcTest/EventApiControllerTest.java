package com.alatoo.socialEventManagement.mockitoAndMockMvcTest;

import com.alatoo.socialEventManagement.controllers.EventApiController;
import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.services.event.EventService;
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
 * Unit tests for the EventApiController class using Mockito.
 */
public class EventApiControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventApiController eventApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for testing the getAllEvents method.
     */
    @Test
    void getAllEvents() {
        // Mocking service response
        List<EventDTO> events = new ArrayList<>();
        events.add(EventDTO.builder().eventId(1L).eventName("event1").build());
        events.add(EventDTO.builder().eventId(2L).eventName("event 2").build());
        when(eventService.findAllEvents()).thenReturn(events);

        // Calling the controller method
        List<EventDTO> result = eventApiController.getAllEvents();

        // Verifying that the expected service method is called
        verify(eventService, times(1)).findAllEvents();

        // Asserting the result
        assertEquals(2, result.size());
    }

    /**
     * Test case for testing the getById method.
     */
    @Test
    void testGetById() {
        // Mocking service response
        EventDTO event = EventDTO.builder().eventId(1L).eventName("Event1").build();
        when(eventService.findEventByID(1L)).thenReturn(Optional.of(event));

        // Calling the controller method
        EventDTO result = eventApiController.getById(1L);

        // Verifying that the expected service method is called
        verify(eventService, times(1)).findEventByID(1L);

        // Asserting the result
        assertEquals(event, result);
    }

    /**
     * Test case for testing the deleteEvent method.
     */
    @Test
    void testDeleteEvent() {
        // Calling the controller method
        eventApiController.deleteEvent(1L);

        // Verifying that the expected service method is called
        verify(eventService, times(1)).deleteEvent(1L);
    }
}
