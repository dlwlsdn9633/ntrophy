package com.ntrophy.controller.api;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.dto.ApiResponse;
import com.ntrophy.dto.comment.CommentRequestDto;
import com.ntrophy.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentApiController {
    private final CommentService commentService;
    @GetMapping("/{postId}")
    public ResponseEntity<?> indexApi(
        @PathVariable("postId") String postId,
        @RequestParam(
                value = "reqPage",
                required = false,
                defaultValue = "0"
        ) int reqPage
    ) {
        List<Comment> list = commentService.list(
                CommentRequestDto.builder()
                        .postId(Integer.parseInt(postId))
                        .startPage(reqPage)
                        .build()
        );
        return ResponseEntity.ok(ApiResponse.success(list));
    }
}
