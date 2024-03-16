package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "createdEvents", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    User userDtoToUser(UserDTO dto);

    @Mapping(target = "createdEvents", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserDTO userToUserDto(User user);
}
