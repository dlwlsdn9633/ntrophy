package com.ntrophy.controller.api;

import com.ntrophy.domain.enums.PostType;
import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.ApiResponse;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeApiController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<?> indexApi(
            @RequestParam(
                    value = "reqPage",
                    required = false,
                    defaultValue = "0"
            ) int reqPage,
            @RequestParam(value = "postType", required = false) String postTypeLabel
    ) {
        PostType postType = PostType.DEFAULT;
        try {
            postType = PostType.fromLabel(postTypeLabel);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        List<Post> postList = postService.list(
                PostRequestDto.builder()
                        .startPage(reqPage)
                        .postType(postType)
                        .build()
        );
        return ResponseEntity.ok(ApiResponse.success(postList));
    }
}
