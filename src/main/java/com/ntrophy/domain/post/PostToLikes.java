package com.ntrophy.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostToLikes {
    private int id;
    private int postFk;
    private int memberFk;
    private String uuid;
    private int count;
    private int likesNumber;
}