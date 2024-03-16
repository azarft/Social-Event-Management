package com.alatoo.socialEventManagement.services.like;

import com.alatoo.socialEventManagement.dto.LikeDTO;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<LikeDTO> findAllLikes();

    Optional<LikeDTO> findLikeById(Long id);

    LikeDTO saveLike(LikeDTO dto);

    void deleteLike(Long id);
}
