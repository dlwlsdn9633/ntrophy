package com.ntrophy.mapper;

import com.ntrophy.domain.post.PostToLikes;

public interface PostToLikesMapper {
    int count(PostToLikes postToLikes);
    int insert(PostToLikes postToLikes);
    int deleteByUUID(PostToLikes postToLikes);
    int checkByUUID(PostToLikes postToLikes);
}
