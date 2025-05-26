package com.ntrophy.mapper;


import com.ntrophy.domain.post.Post;

import java.util.List;


public interface PostMapper {
    int insert(Post post);
    int update(Post post);
    List<Post> list(Post post);
    Post read(Post post);

}
