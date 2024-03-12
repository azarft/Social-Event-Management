package com.alatoo.socialEventManagement.repositories;

import com.alatoo.socialEventManagement.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

}
