package com.ntrophy.mapper;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.domain.post.Post;

import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    List<Comment> list(Comment comment);
    int countByPostId(Comment comment);
}
