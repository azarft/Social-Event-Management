package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.entities.Event;
import org.mapstruct.Mapper;

@Mapper
public interface EventMapper {
    Event eventDtoToEvent(EventDTO dto);

    EventDTO eventToEventDto(Event event);
}
