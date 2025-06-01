package com.ntrophy.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentRequestDto {
    private int id;
    private int postId;
    private String contents;
    private int startPage;
}
