package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.entities.Like;
import org.mapstruct.Mapper;

@Mapper
public interface LikeMapper {
    Like likeDtoToLike(LikeDTO dto);

    LikeDTO likeToLikeDto(Like like);
}
