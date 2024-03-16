package com.alatoo.socialEventManagement.repositoryTest;
import com.alatoo.socialEventManagement.entities.Comment;
import com.alatoo.socialEventManagement.entities.Event;
import com.alatoo.socialEventManagement.entities.Like;
import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.repositories.CommentRepository;
import com.alatoo.socialEventManagement.repositories.LikeRepository;
import com.alatoo.socialEventManagement.repositories.EventRepository;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventRepository userRepository;

    User user = new User();
    @Test
    @DirtiesContext
    public void testSaveAndGetEvent() {
        Event event = Event.builder()
                .eventName("Sample Event")
                .description("This is a sample event description.")
                .location("Sample Location")
                .date(new Date()) // You can set a specific date or leave it as the current date
                .createdBy(user)
                .build();;

        Event savedEvent = eventRepository.save(event);
        assertNotNull(savedEvent.getEventId());

        Event retrievedEvent = eventRepository.findById(savedEvent.getEventId()).orElse(null);
        assertNotNull(retrievedEvent);
        assertEquals(event.getEventId(), retrievedEvent.getEventId());
        // Add more assertions based on your entity structure
    }

    @Test
    @DirtiesContext
    public void testFindAllEvents() {
        // Test a method like findAll in your repository
        Event event1 = new Event();
        // Set properties for event1

        Event event2 = new Event();
        // Set properties for event2

        eventRepository.save(event1);
        eventRepository.save(event2);

        List<Event> events = (List<Event>) eventRepository.findAll();
        assertNotNull(events);
        assertEquals(2, events.size());
    }
}
