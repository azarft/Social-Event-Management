package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.services.like.LikeService;
import com.alatoo.socialEventManagement.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LikeApiController {
    private final String API_PATH = "/api/v1/";
    private final String LIKE_PATH = API_PATH + "like";
    private final String ID_PATH = LIKE_PATH + "/{id}";

    private final LikeService likeService;


}
