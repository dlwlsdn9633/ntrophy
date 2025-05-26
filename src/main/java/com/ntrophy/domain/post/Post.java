package com.ntrophy.domain.post;

import com.ntrophy.domain.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private int views;
    private PostType postType;
    private String password;
    private LocalDateTime registerDate;
    private int startPage;
    private int pageRows;
}
