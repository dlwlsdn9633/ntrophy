package com.ntrophy.repository.post;

import com.ntrophy.domain.post.Post;

import java.util.List;

public interface PostRepository {
    List<Post> list(Post post);
    int insert(Post post);
    Post read(Post post);
}
