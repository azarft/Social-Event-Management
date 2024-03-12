package com.alatoo.socialEventManagement;

import com.alatoo.socialEventManagement.entities.Comment;
import com.alatoo.socialEventManagement.entities.Event;
import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.repositories.CommentRepository;
import com.alatoo.socialEventManagement.repositories.EventRepository;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testSaveAndGetComment() {
        User user = new User(/* Initialize User properties */);
        user = userRepository.save(user);

        Event event = new Event(/* Initialize Event properties */);
        event.setCreatedBy(user);
        event = eventRepository.save(event);

        Comment comment = Comment.builder()
                .text("This is a comment.")
                .user(user)
                .event(event)
                .build();

        comment = commentRepository.save(comment);

        Optional<Comment> retrievedComment = commentRepository.findById(comment.getCommentId());
        assertTrue(retrievedComment.isPresent());
    }
}
