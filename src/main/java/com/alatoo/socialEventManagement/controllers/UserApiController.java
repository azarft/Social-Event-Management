package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserApiController {
    private final String API_PATH = "/api/v1/";
    private final String USER_PATH = API_PATH + "user";
    private final String ID_PATH = USER_PATH + "/{id}";

    private final UserService userService;

}
