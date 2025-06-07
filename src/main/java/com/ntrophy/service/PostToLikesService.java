package com.ntrophy.service;

import com.ntrophy.domain.post.PostToLikes;
import com.ntrophy.dto.post.PostToLikesDto;
import com.ntrophy.repository.postToLikes.PostToLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostToLikesService {
    private final PostToLikesRepository postToLikesRepository;
    public int count(int postFk) {
        return postToLikesRepository.count(PostToLikes.builder().postFk(postFk).build());
    }
    public boolean isLike(PostToLikes postToLikes) {
        return postToLikesRepository.checkByUUID(postToLikes);
    }
    public boolean likes(PostToLikes postToLikes) {
        return postToLikesRepository.insert(postToLikes) > 0;
    }
    public boolean dislikes(PostToLikes postToLikes) {
        return postToLikesRepository.deleteByUUID(postToLikes) > 0;
    }
}
