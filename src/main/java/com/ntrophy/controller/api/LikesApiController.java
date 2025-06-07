package com.ntrophy.controller.api;

import com.ntrophy.domain.post.PostToLikes;
import com.ntrophy.dto.ApiResponse;
import com.ntrophy.mapper.PostToLikesMapper;
import com.ntrophy.service.PostToLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/likes")
public class LikesApiController {
    private final PostToLikesService postToLikesService;
    @GetMapping("/{postId}")
    public ResponseEntity<?> getLikesNumber(
            @PathVariable("postId") int postId
    ) {
        int likesNumber = postToLikesService.count(postId);
        PostToLikes postToLikes = PostToLikes.builder()
                .likesNumber(likesNumber)
                .build();
        return ResponseEntity.ok(ApiResponse.success(postToLikes));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> likeApi(
        @PathVariable("postId") int postId,
        @CookieValue("ntrophy-token") String ntrophyToken
    ) {
        PostToLikes postToLikes = PostToLikes.builder()
                .uuid(ntrophyToken)
                .postFk(postId)
                .build();
        if (postToLikesService.isLike(postToLikes)) {
            log.info("like -> dislike");
            postToLikesService.dislikes(postToLikes);
            postToLikes.setCount(-1);
        } else {
            log.info("dislike -> like");
            postToLikesService.likes(postToLikes);
            postToLikes.setCount(1);
        }
        return ResponseEntity.ok(ApiResponse.success(postToLikes));
    }
}
