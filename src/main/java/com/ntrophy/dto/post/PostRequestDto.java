package com.ntrophy.dto.post;

import com.ntrophy.domain.enums.PostType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private int id;
    private String title;
    private String contents;
    private String password;
    private PostType postType;
    private int startPage;
    private String stype;
    private String sval;
}
