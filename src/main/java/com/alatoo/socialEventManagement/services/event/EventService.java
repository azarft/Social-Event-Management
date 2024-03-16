package com.alatoo.socialEventManagement.services.event;

import com.alatoo.socialEventManagement.dto.EventDTO;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDTO> findAllEvents();

    Optional<EventDTO> findEventByID(Long id);

    EventDTO saveEvent(EventDTO dto);

    void deleteEvent(Long id);
}
