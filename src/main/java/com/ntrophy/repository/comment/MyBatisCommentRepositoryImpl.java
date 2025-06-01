package com.ntrophy.repository.comment;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisCommentRepositoryImpl implements CommentRepository {
    private final CommentMapper mapper;
    @Override
    public int insert(Comment comment) {
        return mapper.insert(comment);
    }
    @Override
    public List<Comment> list(Comment comment) {
        return mapper.list(comment);
    }
    @Override
    public int countByPostId(Comment comment) {
        return mapper.countByPostId(comment);
    }
}
