package com.ntrophy.dto.post;

import com.ntrophy.domain.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;
    private String password;
    private PostType postType;
}
