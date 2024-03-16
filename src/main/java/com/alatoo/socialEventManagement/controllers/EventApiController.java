package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.services.event.EventService;
import com.alatoo.socialEventManagement.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EventApiController {
    private final String API_PATH = "/api/v1/";
    private final String EVENT_PATH = API_PATH + "event";
    private final String ID_PATH = EVENT_PATH + "/{id}";

    private final EventService eventService;

}
