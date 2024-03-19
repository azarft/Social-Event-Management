package com.alatoo.socialEventManagement.services.like;

import com.alatoo.socialEventManagement.controllers.exceptions.NotFoundException;
import com.alatoo.socialEventManagement.dto.LikeDTO;
import com.alatoo.socialEventManagement.entities.Like;
import com.alatoo.socialEventManagement.mappers.LikeMapper;
import com.alatoo.socialEventManagement.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceJPA implements LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeServiceJPA(LikeMapper likeMapper, LikeRepository likeRepository) {
        this.likeMapper = likeMapper;
        this.likeRepository = likeRepository;
    }

    @Override
    public List<LikeDTO> findAllLikes() {
        List<Like> likes = (List<Like>) likeRepository.findAll();
        return likes.stream()
                .map(likeMapper::likeToLikeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LikeDTO> findLikeById(Long id) {
        Optional<Like> optionalLike = likeRepository.findById(id);
        Like like = optionalLike.orElseThrow(() -> new NotFoundException("Like not found with id: " + id));
        return Optional.of(likeMapper.likeToLikeDto(like));
    }

    @Override
    public LikeDTO saveLike(LikeDTO dto) {
        Like savedLike = likeRepository.save(likeMapper.likeDtoToLike(dto));
        return likeMapper.likeToLikeDto(savedLike);
    }

    @Override
    public void deleteLike(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new NotFoundException("Like not found with id: " + id);
        }
        likeRepository.deleteById(id);
    }
}

