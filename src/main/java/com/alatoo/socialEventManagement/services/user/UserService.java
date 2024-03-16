package com.alatoo.socialEventManagement.services.user;

import com.alatoo.socialEventManagement.dto.UserDTO;

import java.util.Optional;
import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    Optional<UserDTO> findUserByID(Long id);

    UserDTO saveUser(UserDTO dto);

    void deleteUser(Long id);
}
