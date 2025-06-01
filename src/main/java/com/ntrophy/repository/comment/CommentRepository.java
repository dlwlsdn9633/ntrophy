package com.ntrophy.repository.comment;

import com.ntrophy.domain.comment.Comment;

import java.util.List;

public interface CommentRepository {
    int insert(Comment comment);
    List<Comment> list(Comment comment);
    int countByPostId(Comment comment);
}
