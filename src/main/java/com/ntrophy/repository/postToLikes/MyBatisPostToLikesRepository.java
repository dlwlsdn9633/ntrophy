package com.ntrophy.repository.postToLikes;

import com.ntrophy.domain.post.PostToLikes;
import com.ntrophy.mapper.PostToLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisPostToLikesRepository implements PostToLikesRepository {
    private final PostToLikesMapper mapper;
    @Override
    public int insert(PostToLikes postToLikes) {
        return mapper.insert(postToLikes);
    }
    @Override
    public int deleteByUUID(PostToLikes postToLikes) {
        return mapper.deleteByUUID(postToLikes);
    }
    @Override
    public boolean checkByUUID(PostToLikes postToLikes) {
        return mapper.checkByUUID(postToLikes) > 0;
    }
    @Override
    public int count(PostToLikes postToLikes) {
        return mapper.count(postToLikes);
    }
}
