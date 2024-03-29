package com.alatoo.socialEventManagement.controllers;

import com.alatoo.socialEventManagement.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.services.like.LikeService;
//import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1")

public class LikeApiController {

    private final String LIKE_PATH = "/like";
    private final String ID_PATH = LIKE_PATH + "/{id}";

    private final LikeService likeService;

    @GetMapping(LIKE_PATH)
    public List<LikeDTO> getAllLikes() {
        return likeService.findAllLikes();
    }

    @GetMapping(ID_PATH)
    public LikeDTO getById(@PathVariable Long id) {
        log.info("Getting like with id: {}", id);
        return likeService.findLikeById(id).orElseThrow(() -> new NotFoundException("Like not found with id: " + id));
    }

    @PostMapping(LIKE_PATH)
    public LikeDTO createLike(@Validated @RequestBody LikeDTO likeDTO) {
        return likeService.saveLike(likeDTO);
    }

    @PutMapping(ID_PATH)
    public LikeDTO updateLike(@PathVariable Long id, @Validated @RequestBody LikeDTO likeDTO) {
        likeService.findLikeById(id).orElseThrow(() -> new NotFoundException("Like not found with id: " + id));
        likeDTO.setLikeId(id);
        return likeService.saveLike(likeDTO);
    }

    @DeleteMapping(ID_PATH)
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}
