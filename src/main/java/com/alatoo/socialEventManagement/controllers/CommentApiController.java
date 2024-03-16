package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.services.comment.CommentService;
import com.alatoo.socialEventManagement.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CommentApiController {
    private final String API_PATH = "/api/v1/";
    private final String COMMENT_PATH = API_PATH + "user";
    private final String ID_PATH = COMMENT_PATH + "/{id}";

    private final CommentService commentService;

}
