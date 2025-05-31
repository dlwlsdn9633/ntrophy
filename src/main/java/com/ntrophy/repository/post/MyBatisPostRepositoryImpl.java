package com.ntrophy.repository.post;


import com.ntrophy.domain.post.Post;
import com.ntrophy.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostRepositoryImpl implements PostRepository {
    private final PostMapper mapper;
    @Override
    public int insert(Post post) {
        return mapper.insert(post);
    }
    @Override
    public Post read(Post post) {
        return mapper.read(post);
    }
    @Override
    public List<Post> list(Post post) {
        return mapper.list(post);
    }
    @Override
    public int checkPassword(Post post) {
        return mapper.checkPassword(post);
    }
    @Override
    public int delete(Post post) {
        return mapper.delete(post);
    }
    @Override
    public int update(Post post) {
        return mapper.update(post);
    }
}
