package com.ntrophy.mapper;

import com.ntrophy.domain.comment.Comment;

import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    List<Comment> list(Comment comment);
    Comment read(Comment comment);
}
