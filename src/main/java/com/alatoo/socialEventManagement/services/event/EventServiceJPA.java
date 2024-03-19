package com.alatoo.socialEventManagement.services.event;

import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.entities.Event;
import com.alatoo.socialEventManagement.controllers.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.controllers.exceptions.InvalidRequestException;
import com.alatoo.socialEventManagement.controllers.exceptions.InternalServerErrorException;
import com.alatoo.socialEventManagement.mappers.EventMapper;
import com.alatoo.socialEventManagement.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceJPA implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceJPA(EventMapper eventMapper, EventRepository eventRepository) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDTO> findAllEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventDTO> findEventByID(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        Event event = optionalEvent.orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        return Optional.of(eventMapper.eventToEventDto(event));
    }

    @Override
    public EventDTO saveEvent(EventDTO dto) {
        if (dto == null) {
            throw new InvalidRequestException("EventDTO cannot be null");
        }
        Event savedEvent = eventRepository.save(eventMapper.eventDtoToEvent(dto));
        return eventMapper.eventToEventDto(savedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerErrorException("Failed to delete event with id: " + id);
        }
    }
}

