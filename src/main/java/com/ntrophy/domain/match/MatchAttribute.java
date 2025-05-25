package com.ntrophy.domain.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MatchAttribute {
    private String gameMode;
    private String shardId;
    private boolean isCustomMatch;
    private String createdAt;
    private int duration;
    private String matchType;
    private String seasonState;
    private String titleId;
    private String mapName;
}
