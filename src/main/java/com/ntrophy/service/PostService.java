package com.ntrophy.service;

import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private static final int DEFAULT_PAGE_ROWS = 10;
    private final PostRepository postRepository;
    public Post insert(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .postType(postRequestDto.getPostType())
                .password(postRequestDto.getPassword())
                .build();
        int insertedPost = postRepository.insert(post);
        if (insertedPost <= 0) {
            return null;
        }
        return post;
    }
    @Transactional
    public Post read(PostRequestDto postRequestDto) {
        postRepository.increaseViews(postRequestDto.getId());
        Post post = Post.builder()
                .id(postRequestDto.getId())
                .build();
        return postRepository.read(post);
    }

    public boolean checkPassword(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .id(postRequestDto.getId())
                .password(postRequestDto.getPassword())
                .build();
        return postRepository.checkPassword(post) > 0;
    }

    public List<Post> list(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .postType(postRequestDto.getPostType())
                .stype(postRequestDto.getStype())
                .sval(postRequestDto.getSval())
                .startPage(postRequestDto.getStartPage())
                .pageRows(DEFAULT_PAGE_ROWS)
                .build();
        List<Post> postList = postRepository.list(post);
        postList.forEach(p -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            p.setFormattedRegisterDate(p.getRegisterDate().format(formatter));
        });
        return postList;
    }
    public int delete(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .id(postRequestDto.getId())
                .build();
        return postRepository.delete(post);
    }
    public int update(PostRequestDto postRequestDto) {
        Post post = Post.builder()
                .id(postRequestDto.getId())
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .password(postRequestDto.getPassword())
                .postType(postRequestDto.getPostType())
                .build();
        return postRepository.update(post);
    }


}
