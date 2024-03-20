package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.services.event.EventService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1")
@Api(tags = "Event API")
public class EventApiController {

    private final String EVENT_PATH = "/event";
    private final String ID_PATH = EVENT_PATH + "/{id}";

    private final EventService eventService;

    @GetMapping("/event")
    public List<EventDTO> getAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping(ID_PATH)
    public EventDTO getById(@PathVariable Long id) {
        log.info("Getting event with id: {}", id);
        return eventService.findEventByID(id).orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    @PostMapping(EVENT_PATH)
    public EventDTO createEvent(@Validated @RequestBody EventDTO eventDTO) {
        return eventService.saveEvent(eventDTO);
    }

    @PutMapping(ID_PATH)
    public EventDTO updateEvent(@PathVariable Long id, @Validated @RequestBody EventDTO eventDTO) {
        eventService.findEventByID(id).orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        eventDTO.setEventId(id);
        return eventService.saveEvent(eventDTO);
    }

    @DeleteMapping(ID_PATH)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
