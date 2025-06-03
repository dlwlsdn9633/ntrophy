package com.ntrophy.domain.admin;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminYoutube {
    int id;
    int idx;
    String youtubeUrl;
    private LocalDateTime registerDate;
}