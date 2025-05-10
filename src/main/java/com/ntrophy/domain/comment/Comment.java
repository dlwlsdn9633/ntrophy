package com.ntrophy.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private int postId;
    private String contents;
    private int groupId;
    private int orderInGroupId;
    private int depth;
    private LocalDateTime registerDate;
}
