package com.alatoo.socialEventManagement.repositoryTest;

import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndGetUser() {
        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .name("Test User")
                .build();

        user = userRepository.save(user);

        Optional<User> retrievedUser = userRepository.findById(user.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals(user.getUsername(), retrievedUser.get().getUsername());
        assertEquals(user.getEmail(), retrievedUser.get().getEmail());
    }
}
