package com.alatoo.socialEventManagement.services.like;

import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.entities.Like;
import com.alatoo.socialEventManagement.mappers.LikeMapper;
import com.alatoo.socialEventManagement.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceJPA implements LikeService{
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeServiceJPA(LikeMapper likeMapper, LikeRepository likeRepository) {
        this.likeMapper = likeMapper;
        this.likeRepository = likeRepository;
    }
    @Override
    public List<LikeDTO> findAllLikes() {
        List<Like> events = (List<Like>) likeRepository.findAll();
        return events.stream()
                .map(likeMapper::likeToLikeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LikeDTO> findLikeById(Long id) {
        Optional<Like> optionalLike = likeRepository.findById(id);
        return Optional.ofNullable(
                likeMapper.likeToLikeDto(optionalLike.orElse(null))
        );
    }

    @Override
    public LikeDTO saveLike(LikeDTO dto) {
        Like savedLike = likeRepository.save(likeMapper.likeDtoToLike(dto));
        return likeMapper.likeToLikeDto(savedLike);
    }

    @Override
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}
