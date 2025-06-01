package com.ntrophy.service;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.dto.comment.CommentRequestDto;
import com.ntrophy.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private static final int DEFAULT_PAGE_ROWS = 10;
    private final CommentRepository commentRepository;
    public List<Comment> list(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .postId(commentRequestDto.getPostId())
                .startPage(commentRequestDto.getStartPage())
                .pageRows(DEFAULT_PAGE_ROWS)
                .build();
        List<Comment> commentList = commentRepository.list(comment);
        commentList.forEach(c -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            c.setFormattedRegisterDate(c.getRegisterDate().format(formatter));
        });
        return commentList;
    }
    public int insert(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .playerId(0)
                .postId(commentRequestDto.getPostId())
                .contents(commentRequestDto.getContents())
                .build();
        return commentRepository.insert(comment);
    }
    public int countByPostId(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .postId(commentRequestDto.getPostId())
                .build();
        return commentRepository.countByPostId(comment);
    }
}
