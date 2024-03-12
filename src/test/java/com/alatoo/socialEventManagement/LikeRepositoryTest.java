package com.alatoo.socialEventManagement;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class LikeRepositoryTest {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DirtiesContext
    public void testSaveAndGetLike() {
        // Create a sample User entity (adjust based on your User entity structure)
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setUsername("johndoe");

        // Save the User entity
        userRepository.save(user);

        // Create a sample Like entity
        Like like = new Like();
        like.setUser(user);

        // Save the Like entity
        likeRepository.save(like);

        // Retrieve the saved Like entity by ID
        Optional<Like> retrievedLike = likeRepository.findById(like.getLikeId());

        // Assert that the Like entity is retrieved successfully
        assertTrue(retrievedLike.isPresent());
        assertEquals(user, retrievedLike.get().getUser());
    }

}
