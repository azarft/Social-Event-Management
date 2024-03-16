package com.alatoo.socialEventManagement.services.event;

import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.entities.Event;
import com.alatoo.socialEventManagement.mappers.EventMapper;
import com.alatoo.socialEventManagement.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceJPA implements EventService{
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
        return Optional.ofNullable(
                eventMapper.eventToEventDto(optionalEvent.orElse(null))
        );
    }

    @Override
    public EventDTO saveEvent(EventDTO dto) {
        Event savedEvent = eventRepository.save(eventMapper.eventDtoToEvent(dto));
        return eventMapper.eventToEventDto(savedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
