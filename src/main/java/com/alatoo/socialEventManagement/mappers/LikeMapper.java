package com.alatoo.socialEventManagement.mappers;

import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.entities.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LikeMapper {

    Like likeDtoToLike(LikeDTO dto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)// Ignore user to prevent circular reference
    LikeDTO likeToLikeDto(Like like);
}
