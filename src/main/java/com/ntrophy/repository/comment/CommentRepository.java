package com.ntrophy.repository.comment;

import com.ntrophy.domain.comment.Comment;

public interface CommentRepository {
    int insert(Comment comment);
}
