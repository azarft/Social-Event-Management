package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.services.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Log4j2
@Api(tags = "User API")
public class UserApiController {

    private final String USER_PATH = "/user";
    private final String ID_PATH = USER_PATH + "/{id}";

    private final UserService userService;

    @GetMapping(USER_PATH)
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(ID_PATH)
    public UserDTO getById(@PathVariable Long id) {
        log.info("Getting user with id: {}", id);
        return userService.findUserByID(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @PostMapping(USER_PATH)
    public UserDTO createUser(@Validated @RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PutMapping(ID_PATH)
    public UserDTO updateUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
        userService.findUserByID(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userDTO.setId(id);
        return userService.saveUser(userDTO);
    }

    @DeleteMapping(ID_PATH)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

