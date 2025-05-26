package com.ntrophy.repository.post;


import com.ntrophy.domain.post.Post;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisPostRepositoryImpl implements PostRepository {
    @Override
    public int insert(Post post) {
        return 0;
    }
}
