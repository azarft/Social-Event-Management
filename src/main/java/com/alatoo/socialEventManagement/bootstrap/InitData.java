package com.alatoo.socialEventManagement.bootstrap;

import com.alatoo.socialEventManagement.entities.Comment;
import com.alatoo.socialEventManagement.entities.Event;
import com.alatoo.socialEventManagement.entities.Like;
import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.repositories.CommentRepository;
import com.alatoo.socialEventManagement.repositories.EventRepository;
import com.alatoo.socialEventManagement.repositories.LikeRepository;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import org.hibernate.engine.transaction.jta.platform.internal.JRun4JtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void run(String... args) {

        Set<User> savedUsers = new HashSet<>();
        Set<Event> savedEvents = new HashSet<>();
        Set<Comment> savedComments = new HashSet<>();
        Set<Like> savedLikes = new HashSet<>();

        for (int i = 0; i < 50; i++) {

            User user = User.builder()
                    .name("User " + i)
                    .username("userft " + i)
                    .email("user" + i + "@gmail.com")
                    .build();
            User savedUser = userRepository.save(user);
            savedUsers.add(savedUser);

        }
        List<User> userList = new ArrayList<>(savedUsers);


        LocalDate startDate = LocalDate.of(2024, 3, 12); // Example date
        Date initialDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        for (int i = 0; i < 50; i++) {
            LocalDate newLocalDate = startDate.plusDays(i);
            Date newDate = Date.from(newLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Event event = Event.builder()
                    .eventName("Event " + i)
                    .description("Event description" + i)
                    .location("Bishkek Street" + i + i + i)
                    .date(newDate)
                    .createdBy(userList.get(i))
                    .build();
            Event savedEvent = eventRepository.save(event);
            savedEvents.add(savedEvent);
        }
        List<Event> eventList = new ArrayList<>(savedEvents);

        for (int i = 0; i < 50; i++) {
            Comment comment = Comment.builder()
                    .text("Comment " + i)
                    .event(eventList.get(i))
                    .user(userList.get(i))
                    .build();
            Comment savedComment = commentRepository.save(comment);
            savedComments.add(savedComment);
        }

        for (int i = 0; i < 50; i++) {
            Like like = Like.builder()
                    .event(eventList.get(i))
                    .user(userList.get(i))
                    .build();
            Like savedLike = likeRepository.save(like);
            savedLikes.add(savedLike);
        }
    }
}
