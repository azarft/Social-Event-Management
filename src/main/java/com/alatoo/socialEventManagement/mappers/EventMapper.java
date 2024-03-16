package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.EventDTO;
import com.alatoo.socialEventManagement.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EventMapper {
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Event eventDtoToEvent(EventDTO dto);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    EventDTO eventToEventDto(Event event);
}
