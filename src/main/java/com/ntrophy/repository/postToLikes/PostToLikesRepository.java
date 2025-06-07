package com.ntrophy.repository.postToLikes;


import com.ntrophy.domain.post.PostToLikes;


public interface PostToLikesRepository {
    int insert(PostToLikes postToLikes);
    int deleteByUUID(PostToLikes postToLikes);
    int count(PostToLikes postToLikes);
    boolean checkByUUID(PostToLikes postToLikes);
}
