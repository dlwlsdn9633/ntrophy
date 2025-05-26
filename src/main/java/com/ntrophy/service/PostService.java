package com.ntrophy.service;

import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Post insert(PostRequestDto postRequestDto) {
        Post post = Post.builder().build();
        return post;
    }

}
