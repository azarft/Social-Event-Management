package com.alatoo.socialEventManagement.mapperTest;

import com.alatoo.socialEventManagement.dto.*;
import com.alatoo.socialEventManagement.entities.*;
import com.alatoo.socialEventManagement.mappers.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappersTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    private final LikeMapper likeMapper = Mappers.getMapper(LikeMapper.class);
    private final CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);


    @Test
    public void testUserDtoToUser() {
        // Given
        UserDTO userDTO = UserDTO.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .build();

        // When
        User user = userMapper.userDtoToUser(userDTO);

        // Then
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }

    @Test
    public void testUserToUserDto() {
        // Given
        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .build();

        // When
        UserDTO userDTO = userMapper.userToUserDto(user);

        // Then
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void testEvenToEventDto(){
        // Given
        Event event = Event.builder()
                .eventName("John Doe's party")
                .description("Birthday party, 15 years old")
                .location("Chicago 13th street")
                .date(new Date())
                .build();

        // When
        EventDTO eventDTO = eventMapper.eventToEventDto(event);

        // Then
        assertEquals(event.getEventName(), eventDTO.getEventName());
        assertEquals(event.getLocation(), eventDTO.getLocation());
        assertEquals(event.getDescription(), eventDTO.getDescription());
        assertEquals(event.getDate(), eventDTO.getDate());
    }

    @Test
    public void testEventDtoToEvent(){
        // Given
        EventDTO eventDTO = EventDTO.builder()
                .eventName("John Doe's party")
                .description("Birthday party, 15 years old")
                .location("Chicago 13th street")
                .date(new Date())
                .build();

        // When
        Event event = eventMapper.eventDtoToEvent(eventDTO);

        // Then
        assertEquals(eventDTO.getEventName(), event.getEventName());
        assertEquals(eventDTO.getLocation(), event.getLocation());
        assertEquals(eventDTO.getDescription(), event.getDescription());
        assertEquals(eventDTO.getDate(),event.getDate());
    }

    @Test
    public void testLikeToLikeDto() {
        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .build();
        Event event = Event.builder()
                .eventName("John Doe's party")
                .description("Birthday party, 15 years old")
                .location("Chicago 13th street")
                .date(new Date())
                .build();

        Like like = Like.builder()
                .user(user)
                .event(event)
                .build();

        LikeDTO likeDTO = likeMapper.likeToLikeDto(like);

        //User part
        assertEquals(like.getUser().getName(),likeDTO.getUser().getName());
        assertEquals(like.getUser().getUsername(),likeDTO.getUser().getUsername());
        assertEquals(like.getUser().getEmail(),likeDTO.getUser().getEmail());

        //Event part
        assertEquals(like.getEvent().getEventName(), likeDTO.getEvent().getEventName());
        assertEquals(like.getEvent().getDescription(), likeDTO.getEvent().getDescription());
        assertEquals(like.getEvent().getLocation(), likeDTO.getEvent().getLocation());
        assertEquals(like.getEvent().getDate(), likeDTO.getEvent().getDate());

    }

    @Test
    public void testLikeDtoToLike() {
        UserDTO userDTO = UserDTO.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .build();
        EventDTO eventDTO = EventDTO.builder()
                .eventName("John Doe's party")
                .description("Birthday party, 15 years old")
                .location("Chicago 13th street")
                .date(new Date())
                .build();

        LikeDTO likeDTO = LikeDTO.builder()
                .user(userDTO)
                .event(eventDTO)
                .build();

        Like like = likeMapper.likeDtoToLike(likeDTO);

        //User part
        assertEquals(likeDTO.getUser().getName(),like.getUser().getName());
        assertEquals(likeDTO.getUser().getUsername(),like.getUser().getUsername());
        assertEquals(likeDTO.getUser().getEmail(),like.getUser().getEmail());

        //Event part
        assertEquals(likeDTO.getEvent().getEventName(), like.getEvent().getEventName());
        assertEquals(likeDTO.getEvent().getDescription(), like.getEvent().getDescription());
        assertEquals(likeDTO.getEvent().getLocation(), like.getEvent().getLocation());
        assertEquals(likeDTO.getEvent().getDate(), like.getEvent().getDate());
    }

    @Test
    public void testCommentToCommentDto() {
        Comment comment = Comment.builder()
                .text("John Does party will be fantastic")
                .build();

        CommentDTO commentDTO = commentMapper.commentToCommentDto(comment);

        assertEquals(comment.getText(),commentDTO.getText());
    }

    @Test
    public void testCommentDtoToComment() {
        CommentDTO commentDTO = CommentDTO.builder()
                .text("John Does party will be fantastic")
                .build();

        Comment comment = commentMapper.commentDtoToComment(commentDTO);

        assertEquals(commentDTO.getText(),comment.getText());
    }
}