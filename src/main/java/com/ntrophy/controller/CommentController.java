package com.ntrophy.controller;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.dto.comment.CommentRequestDto;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/write/{postId}")
    public String insert(
            @PathVariable("postId") String postId,
            @ModelAttribute CommentRequestDto commentRequestDto
    ) {
        commentRequestDto.setPostId(Integer.parseInt(postId));
        int insertResult = commentService.insert(commentRequestDto);
        log.info("insert: {}", insertResult);
        if (insertResult > 0) {
            return "redirect:/notice/view/" + postId;
        }
        return "redirect:/notice";
    }
}
